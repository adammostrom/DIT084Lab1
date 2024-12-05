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
            if isFlipped 
            then read_position >= write_position
            else write_position >= read_position
        );

        validArrayIndexes && correctPointerPos
    }

    // A predicate indicating no more Read available
    predicate isEmpty()
    requires Valid()
        reads this
    {
        read_position == write_position && !isFlipped
    }

    // A predicate indicating no more Write should be allowed
    predicate isFull()
    requires Valid()
        reads this
    {
        write_position == read_position && isFlipped
    }


    method Read() returns (isSuccess : bool, content : int)
        modifies this
        requires Valid()
        ensures  Valid()
        ensures  isSuccess ==> (
            var prev := if read_position - 1 < 0 then cells.Length - 1 else read_position - 1;

            content == cells[prev]
        )
        ensures !isSuccess ==> isEmpty()
    {
        if (isEmpty())
        {
            return false, 0;
        }
        
        var read_val := cells[read_position];

        read_position := read_position + 1;
        if (read_position == cells.Length)
        {
            read_position := 0;
            isFlipped := false;
        }

        return true, read_val;
    }

    method Write(input : int) returns (isSuccess : bool)
        modifies this
        modifies cells
        requires Valid()
        ensures  Valid()
        ensures  isSuccess ==> (
            var prev := if write_position - 1 < 0 then cells.Length - 1 else write_position - 1;

            input == cells[prev]
        )
        ensures !isSuccess ==> isFull()
    {
        if (isFull())
        {
            return false;
        }

        cells[write_position] := input;
        
        write_position := write_position + 1;
        if (write_position == cells.Length)
        {
            write_position := 0;
            isFlipped := true;
        }

        return true;
    }


}