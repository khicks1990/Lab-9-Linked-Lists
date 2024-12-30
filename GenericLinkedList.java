
import java.util.Iterator;
import java.util.NoSuchElementException;


// Make tne class implement Iterable so it can have an iterator.
public class GenericLinkedList<E> implements Iterable<E>
{  
    /**
       The Node class stores a list element
       and a reference to the next node.
    */
    
    private class Node
    {
        E value;   // Value of a list element
        Node next;      // Next node in the list
        
        /**
           Constructor.            
           @param val The element to be stored in the node.
           @param n The reference to the successor node.
        */
        
        Node(E val, Node n)
        {
            value = val;
            next = n;
        } 
        
        /**
           Constructor. Use when the node has no successor.
           @param val The element to be stored in the node.
        */
        
        Node(E val)
        {
           // Just call the other (sister) constructor
           this(val, null);            
        }
    }
    
    private Node first;   // First element on the list (head)
    private Node last;    // Last element on the list 
    
    /**
       Constructor.
    */    
    public GenericLinkedList()
    {
        clear();            
    }
    
    /**
       The clear method removes all elements from the list.       
    */
    
    public final void clear()
    {
       first = null;
       last = null;
    }    
    
    /**
       The get method returns the element at a given position in the list.
       @param index specifies a position within the list.
       @return The list element at the specified position.
       @exception IndexOutOfBoundsException when the index is
                  does not correspond to a position in the array.
    */    
    public E get(int index)
    {
       if (index < 0 || index >= size())
       {
           throw new IndexOutOfBoundsException();
       }
       
       // Walk down to the right position
       Node p = first;
       int pos = 0;
       while (pos < index)
       {
           p = p.next;
           pos ++;
       }
       // pos == index and p is pointing to element at position p = index
       return p.value;       
    }
    
    
    /**
       The set method replaces the element at a specified position
       with a given element.
       @param index  A position in the list.
       @param element The new element to store at the given position.
       @return The original element at the given index.
       @exception IndexOutOfBoundsException when the specified index
           does not identify a position within the list.
    */    
    public E set(int index, E element)
    {
       if (index < 0 || index >= size())
       {
           throw new IndexOutOfBoundsException();
       }
       
       // Walk down to the right position
       Node p = first;
       int pos = 0;
       while (pos < index)
       {
           p = p.next;
           pos ++;
       }
       // pos == index and p is pointing to element at position p = index
       
       // Set the new element and return the old
       E oldElement = p.value;
       p.value = element;
       return oldElement;
    }
    
    /**
       The isEmpty method checks to see if the list is empty.
    * @return true if list is empty
    */    
    public boolean isEmpty()
    {        
        return first == null;       
    }
    
    /**
       The size method returns the length of the list.
       @return The number of elements in the list.
    */    
    public int size()
    {
       int count = 0;
       Node p = first;     // Use  p to walk down the list
       while (p != null)
       {
           // There is an element at p
           count ++;
           p = p.next;
       }
       return count;
    }
    
    /**
       The add method adds an element to the end of the list.
       @param e The value to add to the end of the list.       
    */    
    public void add(E e)
    {
      if (isEmpty()) 
      {
          first = new Node(e);
          last = first;
      }
      else
      {
          // Add to end of existing list
          last.next = new Node(e);
          last = last.next;
      }      
    }
    
    /**
       This add method adds an element at an index.
       @param e The element to add to the list.
       @param index The index at which to add the element.
       @exception IndexOutOfBoundsException When index is 
                  out of bounds.  
    */    
    public void add(int index, E e)
    {
         if (index < 0  || index > size()) 
         {
             String message = String.valueOf(index);
             throw new IndexOutOfBoundsException(message);
         }
         
         // Index is at least 0
         if (index == 0)
         {
             // New element goes at beginning
             first = new Node(e, first);
             if (last == null)
                 last = first;
             return;
         }
         
         // Set a reference pred to point to the node that
         // will be the predecessor of the new node
         Node pred = first;        
         for (int k = 1; k <= index - 1; k++)        
         {
            pred = pred.next;           
         }
         
         // Splice in a node containing the new element
         pred.next = new Node(e, pred.next);  
         
         // Is there a new last element ?
         if (pred.next.next == null)
             last = pred.next;         
    }
    
    /**
       The toString method computes the string
       representation of the list.
       @return The string form of the list.
    */    
    @Override
    public String toString()
    {
      StringBuilder strBuilder = new StringBuilder();
      
      // Use p to walk down the linked list
      Node p = first;
      while (p != null)
      {
         strBuilder.append(p.value).append("\n"); 
         p = p.next;
      }      
      return strBuilder.toString(); 
    }
    
    /**
       The remove method removes the element at an index.
       @param index The index of the element to remove. 
       @return The element removed. 
       @exception IndexOutOfBoundsException When index is 
                  out of bounds.      
    */
    
    public E remove(int index)
    {
       if (index < 0 || index >= size())
       {  
           String message = String.valueOf(index);
           throw new IndexOutOfBoundsException(message);
       }
       
       E element;  // The element that will be returned
      
       if (index == 0)
       {
          // Removal of first item in the list
          element = first.value;    
          first = first.next;
          if (first == null)
              last = null;             
       }
       else
       {
          // To remove an element other than the first,
          // find the predecessor of the element to
          // be removed.
          Node pred = first;
          
          // Move pred forward index - 1 times
          for (int k = 1; k <= index -1; k++)
              pred = pred.next;
          
          // Store the value to return
          element = pred.next.value;
          
          // Route link around the node to be removed
          pred.next = pred.next.next;  
          
          // Check if pred is now last
          if (pred.next == null)
              last = pred;              
       }
       return element;        
    }  
    
    /**
       The remove method removes an element from the list.
       @param element The element to remove.
       @return true if the remove succeeded, false otherwise.
    */    
    public boolean remove(E element)
    {
       if (isEmpty()) 
           return false;      
      
       if (element.equals(first.value))
       {
          // Removal of first item in the list
          first = first.next;
          if (first == null)
              last = null;       
          return true;
       }
      
      // Find the predecessor of the element to remove
      Node pred = first;
      while (pred.next != null && 
             !pred.next.value.equals(element))
      {
          pred = pred.next;
      }

      // pred.next == null OR pred.next.value is element
      if (pred.next == null)
          return false;
      
      // pred.next.value  is element
      pred.next = pred.next.next;    
      
      // Check if pred is now last
      if (pred.next == null)
          last = pred;
      
      return true;       
    }    
     
    
   @Override
   public Iterator<E> iterator()
   {
      return new MyListIterator();
   }
   
   class MyListIterator implements Iterator<E>
   {
      // Track the node containing the value
      // to be returned by the next call to the 
      // iterator.
      Node tracker = first;  
      
      @Override
      public boolean hasNext()
      {
         return tracker != null;
      }

      @Override
      public E next()
      {
         if (tracker == null) throw new NoSuchElementException();
         E nextValue = tracker.value;
         tracker = tracker.next;
         return nextValue;
      }      
   }
}

