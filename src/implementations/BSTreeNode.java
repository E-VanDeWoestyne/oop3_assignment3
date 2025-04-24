package implementations;

/**
 * Node class for Binary Search Tree implementation. Each node contains an
 * element and references to left and right child nodes.
 * 
 * @author Ethan Van De Woestyne
 * @param <E> The type of element this node holds.
 */
public class BSTreeNode<E>
{
	private E element;
	private BSTreeNode<E> left;
	private BSTreeNode<E> right;

	/**
	 * Constructs a BSTreeNode with the specified element.
	 * 
	 * @param element The element to be stored in this node.
	 */
	public BSTreeNode(E element)
	{
		this.element = element;
		this.left = null;
		this.right = null;
	}

	/**
	 * Gets the element stored in this node.
	 * 
	 * @return The element stored in this node.
	 */
	public E getElement()
	{
		return element;
	}

	/**
	 * Sets the element stored in this node.
	 * 
	 * @param element The element to be stored in this node.
	 */
	public void setElement(E element)
	{
		this.element = element;
	}

	/**
	 * Gets the left child node.
	 * 
	 * @return The left child node.
	 */
	public BSTreeNode<E> getLeft()
	{
		return left;
	}

	/**
	 * Sets the left child node.
	 * 
	 * @param left The left child node.
	 */
	public void setLeft(BSTreeNode<E> left)
	{
		this.left = left;
	}

	/**
	 * Gets the right child node.
	 * 
	 * @return The right child node.
	 */
	public BSTreeNode<E> getRight()
	{
		return right;
	}

	/**
	 * Sets the right child node.
	 * 
	 * @param right The right child node.
	 */
	public void setRight(BSTreeNode<E> right)
	{
		this.right = right;
	}
}