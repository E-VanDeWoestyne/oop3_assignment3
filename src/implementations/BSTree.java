package implementations;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import utilities.BSTreeADT;
import utilities.Iterator;

/**
 * Binary Search Tree implementation. Implements the BSTreeADT interface.
 * 
 * @author Ethan Van De Woestyne
 * @param <E> The type of elements this tree holds.
 */
public class BSTree<E extends Comparable<? super E>> implements BSTreeADT<E>
{
	private BSTreeNode<E> root;
	private int size;

	/**
	 * Default constructor. Creates an empty tree.
	 */
	public BSTree()
	{
		root = null;
		size = 0;
	}

	/**
	 * Constructor that initializes the tree with a single element.
	 * 
	 * @param element The element to initialize the tree with.
	 */
	public BSTree(E element)
	{
		if (element == null)
			throw new NullPointerException("Cannot create tree with null element");

		root = new BSTreeNode<>(element);
		size = 1;
	}

	@Override
	public BSTreeNode<E> getRoot() throws NullPointerException
	{
		if (root == null)
			throw new NullPointerException("Tree is empty");
		return root;
	}

	@Override
	public int getHeight()
	{
		if (root == null)
			return 0;

		return getHeight(root);
	}

	private int getHeight(BSTreeNode<E> node)
	{
		if (node == null)
			return 0;

		int leftHeight = getHeight(node.getLeft());
		int rightHeight = getHeight(node.getRight());

		return Math.max(leftHeight, rightHeight) + 1;
	}

	@Override
	public int size()
	{
		return size;
	}

	@Override
	public boolean isEmpty()
	{
		return (root == null);
	}

	@Override
	public void clear()
	{
		root = null;
		size = 0;
	}

	@Override
	public boolean contains(E entry) throws NullPointerException
	{
		if (entry == null)
			throw new NullPointerException("Cannot search for null entry");

		return search(entry) != null;
	}

	@Override
	public BSTreeNode<E> search(E entry) throws NullPointerException
	{
		if (entry == null)
			throw new NullPointerException("Cannot search for null entry");

		BSTreeNode<E> current = root;

		while (current != null)
		{
			int comparison = entry.compareTo(current.getElement());

			if (comparison == 0)
				return current;
			else if (comparison < 0)
				current = current.getLeft();
			else
				current = current.getRight();
		}

		return null;
	}

	@Override
	public boolean add(E newEntry) throws NullPointerException
	{
		if (newEntry == null)
			throw new NullPointerException("Cannot add null entry");

		if (root == null)
		{
			root = new BSTreeNode<>(newEntry);
			size++;
			return true;
		}

		BSTreeNode<E> parent = null;
		BSTreeNode<E> current = root;
		int comparison = 0;

		// Find the insertion point
		while (current != null)
		{
			parent = current;
			comparison = newEntry.compareTo(current.getElement());

			if (comparison == 0)
				return false; // Element already exists
			else if (comparison < 0)
				current = current.getLeft();
			else
				current = current.getRight();
		}

		if (comparison < 0)
			parent.setLeft(new BSTreeNode<>(newEntry));
		else
			parent.setRight(new BSTreeNode<>(newEntry));

		size++;
		return true;
	}

	@Override
	public BSTreeNode<E> removeMin()
	{
		if (root == null)
			return null;

		if (root.getLeft() == null)
		{
			BSTreeNode<E> minNode = root;
			root = root.getRight();
			size--;
			return minNode;
		}

		BSTreeNode<E> parent = root;
		BSTreeNode<E> current = root.getLeft();

		while (current.getLeft() != null)
		{
			parent = current;
			current = current.getLeft();
		}

		parent.setLeft(current.getRight());
		size--;
		return current;
	}

	@Override
	public BSTreeNode<E> removeMax()
	{
		if (root == null)
			return null;

		if (root.getRight() == null)
		{
			BSTreeNode<E> maxNode = root;
			root = root.getLeft();
			size--;
			return maxNode;
		}

		BSTreeNode<E> parent = root;
		BSTreeNode<E> current = root.getRight();

		while (current.getRight() != null)
		{
			parent = current;
			current = current.getRight();
		}

		parent.setRight(current.getLeft());
		size--;
		return current;
	}

	@Override
	public Iterator<E> inorderIterator()
	{
		final ArrayList<E> elements = new ArrayList<>();
		inorderTraversal(root, elements);

		return new Iterator<E>()
		{
			private int currentIndex = 0;

			@Override
			public boolean hasNext()
			{
				return currentIndex < elements.size();
			}

			@Override
			public E next() throws NoSuchElementException
			{
				if (!hasNext())
					throw new NoSuchElementException("No more elements in the iteration");
				return elements.get(currentIndex++);
			}
		};
	}

	private void inorderTraversal(BSTreeNode<E> node, ArrayList<E> elements)
	{
		if (node != null)
		{
			inorderTraversal(node.getLeft(), elements);
			elements.add(node.getElement());
			inorderTraversal(node.getRight(), elements);
		}
	}

	@Override
	public Iterator<E> preorderIterator()
	{
		final ArrayList<E> elements = new ArrayList<>();
		preorderTraversal(root, elements);

		return new Iterator<E>()
		{
			private int currentIndex = 0;

			@Override
			public boolean hasNext()
			{
				return currentIndex < elements.size();
			}

			@Override
			public E next() throws NoSuchElementException
			{
				if (!hasNext())
					throw new NoSuchElementException("No more elements in the iteration");
				return elements.get(currentIndex++);
			}
		};
	}

	private void preorderTraversal(BSTreeNode<E> node, ArrayList<E> elements)
	{
		if (node != null)
		{
			elements.add(node.getElement());
			preorderTraversal(node.getLeft(), elements);
			preorderTraversal(node.getRight(), elements);
		}
	}

	@Override
	public Iterator<E> postorderIterator()
	{
		final ArrayList<E> elements = new ArrayList<>();
		postorderTraversal(root, elements);

		return new Iterator<E>()
		{
			private int currentIndex = 0;

			@Override
			public boolean hasNext()
			{
				return currentIndex < elements.size();
			}

			@Override
			public E next() throws NoSuchElementException
			{
				if (!hasNext())
					throw new NoSuchElementException("No more elements in the iteration");
				return elements.get(currentIndex++);
			}
		};
	}

	private void postorderTraversal(BSTreeNode<E> node, ArrayList<E> elements)
	{
		if (node != null)
		{
			postorderTraversal(node.getLeft(), elements);
			postorderTraversal(node.getRight(), elements);
			elements.add(node.getElement());
		}
	}
}