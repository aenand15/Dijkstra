package Dijkstra;


public class MinBinHeap implements Heap_Interface {
  private EntryPair[] array; //load this array
  private int size;
  private static final int arraySize = 100; //Everything in the array will initially 
                                              //be null. This is ok! Just build out 
                                              //from array[1]

  public MinBinHeap() {
    this.array = new EntryPair[arraySize];
    array[0] = new EntryPair(null, -100000); //0th will be unused for simplicity 
                                             //of child/parent computations...
                                             //the book/animation page both do this.
  }
    
  //Please do not remove or modify this method! Used to test your entire Heap.
  @Override
  public EntryPair[] getHeap() { 
    return this.array;
  }

@Override
public void insert(EntryPair entry) {
	// TODO Auto-generated method stub
	size++;

	array[size] = entry;
	bubble(size);
}

@Override
public void delMin() {
	// TODO Auto-generated method stub
	array[1] = array[size];
	array[size] = null;
	size--;
	bubbleDown(1);
}

@Override
public EntryPair getMin() {
	// TODO Auto-generated method stub
	if(array[1] == null){
		return null;
	}else{
		return array[1];
	}
}

@Override
public int size() {
	// TODO Auto-generated method stub
	return size;
}

@Override
public void build(EntryPair[] entries) {
	// TODO Auto-generated method stub
	int x=0;
	for(int i = 1; i <= entries.length; i++){
			array[i] = entries[i-1];
			size++;
		
	}
	for(int i = size; i > 0; i--){
		if(leftChild(i) != null){
			x = i;
			break;
		}
	}for(int i = x; i >= 0; i--){
		bubbleDown(i);
	}
}

private void bubble(int x){
	EntryPair temp;
	int y = x;
	if(x % 2 == 0){
		//compare with size divided by two
		y = y /2;
		if(array[x].getPriority() < array[y].getPriority()){
			temp = array[x];
			array[x] = array[y];
			array[y] = temp;
			bubble(y);
		}else{
			return;
		}
	}else{
		//compare with size - 1 divided by two
		y--;
		y = y / 2;
		if(array[x].getPriority() < array[y].getPriority()){
			temp = array[x];
			array[x] = array[y];
			array[y] = temp;
			bubble(y);
		}else{
			return;
		}
	}
}

private void bubbleDown(int hole){
	//deletemin and build
	EntryPair right = rightChild(hole);
	EntryPair left = leftChild(hole);
	if(right != null && array[hole].getPriority() > right.getPriority()){
		if(left.getPriority() < right.getPriority()){
			EntryPair temp = array[hole];
			array[hole] = left;
			hole = hole*2;
			array[hole] = temp;
			bubbleDown(hole);
		}else{
		EntryPair temp = array[hole];
		array[hole] = right;
		hole = hole*2;
		hole++;
		array[hole] = temp;
		bubbleDown(hole);
		}
	}else if(left != null && array[hole].getPriority() > left.getPriority()){
		EntryPair temp = array[hole];
		array[hole] = left;
		hole = hole*2;
		array[hole] = temp;
		bubbleDown(hole);
	}
}
private EntryPair leftChild(int n){
	n = n*2;
	return array[n];
}
private EntryPair rightChild(int n){
	n = n*2;
	n++;
	return array[n];
}
}

