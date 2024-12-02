// class CircularMemory
// This class implements a circular buffer with 2 int typed pointers

// We start with a basic snippet for our circular buffer. 
// The first task is to complete this constructor by supplying sufficient pre-condition.
class CircularMemory
{
  var cells : array<int>
  var read_position : int
  var write_position : int
  var isFlipped : bool

  constructor Init(cap : int)
  requires cap >= 0
  {
    cells := new int[cap];
    read_position, write_position := 0, 0;
    isFlipped := false;
  }

    // Valid is the class invariant we would like to maintain
  // for any CircularMemory at any moment of its lifetime
  predicate Valid()
    reads this
  {

    // Pointer positioning in array 
    var validArrayIndexes := read_position >= 0 
    && write_position >= 0 
    && read_position < cells.Length 
    && write_position < cells.Length;

    // Validate isFlipped with respect to read/write position 
    var correctPointerPos := (
          if isFlipped then read_position > write_position
          else write_position >= read_position
    );

  
  validArrayIndexes && correctPointerPos

  }

  // A predicate indicating no more Read available
  predicate isEmpty()
  requires Valid()
    reads this
  {
    read_position == write_position 
  }

  //A predicate indicating no more Write should be allowed
  predicate isFull()
  requires Valid()
    reads this
  {
  
  if isFlipped then 
    write_position == read_position - 1
  else 
    write_position == cells.Length -1 && read_position == 0   
    
  }
}





  

