package queue;
import java.util.NoSuchElementException;

public class Queue<T> implements UnboundedQueueInterface<T> {
	
	private final int DEFAULT_CAP = 52;
	private T[] queue;
	private int size;
	private int front;
	private int rear;
	
	@SuppressWarnings("unchecked")
	public Queue() {
		// TODO 1
		 queue = (T[])new Object[DEFAULT_CAP];
		 rear = DEFAULT_CAP - 1;
		 front = 0;
		 size = 0;
	}
	
	@SuppressWarnings("unchecked")
	public Queue(Queue<T> other) {
		queue = (T[])new Object[DEFAULT_CAP];
		rear = DEFAULT_CAP - 1;
		front = 0;
		size = 0;
		int count = other.front;
		T item = other.queue[other.front];
		while(item != null)
		{
			this.enqueue(item);
			count++;
			item = other.queue[count];
		}
	}
	
	public boolean isEmpty() {
		// TODO 3
		return size == 0;
	}
	public boolean isFull() {
		// TODO 3
		return size == 52;
	}

	public int size() {
		// TODO 4
		return size;
	}

	public void enqueue(T element)  {
		// TODO 5;
		    rear = (rear + 1) % queue.length;
		    queue[rear] = element;
		    size++;
	}
	
	public void showQueue()
	{
		for(int i = front; i < queue.length + front; i++)
		{
			if(queue[i % queue.length] != null)
				System.out.print(queue[i % queue.length] + " ");
		}
	}

	public T dequeue() throws NoSuchElementException {
		// TODO 6;
		 if (isEmpty()) 
		      return null;
	    T value = queue[front];
	    queue[front] = null;
	    front = (front + 1) % queue.length;
	    size--;
	    return value;
	}

	public T peek() throws NoSuchElementException {
		// TODO 7
		return queue[front];
	}

	public UnboundedQueueInterface<T> reversed() {
		 UnboundedQueueInterface<T> reverse = new Queue<T>();
		 int count = 0;
		 while(count != size)
		 {
			 reverse.enqueue(queue[rear - count]);
			 count++;
		 }
		return reverse;
	}
}
