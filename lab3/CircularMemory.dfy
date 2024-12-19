class CircularMemory
{
    var cells : array<int>;
    var read_position : int;
    var write_position : int;
    var isFlipped : bool;

    constructor Init(cap : int)
        requires cap >= 0
    {
        cells := new int[cap];
        read_position, write_position := 0, 0;
        isFlipped := false;
    }

    predicate Valid()
        reads this
    {
        var validArrayIndexes := read_position >= 0 
            && write_position >= 0 
            && read_position < cells.Length 
            && write_position < cells.Length;

        var correctPointerPos := (
            if isFlipped 
            then read_position >= write_position
            else write_position >= read_position
        );

        validArrayIndexes && correctPointerPos
    }

    predicate isEmpty()
        requires Valid()
        reads this
    {
        read_position == write_position && !isFlipped
    }

    predicate isFull()
        requires Valid()
        reads this
    {
        write_position == read_position && isFlipped
    }

    method DoubleCapacity()
        modifies this, cells
        requires Valid()
        ensures Valid()
        ensures cells.Length == 2 * old(cells.Length)
        ensures read_position == old(read_position)
        ensures write_position == old(write_position)
        ensures forall j : int :: 0 <= j < old(cells.Length) ==> cells[j] == old(cells[j])
        ensures forall j : int :: old(cells.Length) <= j < cells.Length ==> cells[j] == 0
    {
        var old_len := cells.Length;
        var new_cells := new int[2 * old_len];
        var i := 0;

        while i < 2 * old_len
            invariant 0 <= i <= 2 * old_len
            invariant forall j :: 0 <= j < i ==> 
                (j < cells.Length ==> new_cells[j] == cells[j]) &&
                (j >= old_len ==> new_cells[j] == 0)
            invariant new_cells.Length == 2 * old_len
            invariant read_position == old(read_position)
            invariant write_position == old(write_position)
            invariant isFlipped == old(isFlipped)
            invariant cells == old(cells)
            invariant forall j :: 0 <= j < cells.Length ==> cells[j] == old(cells[j])
            decreases 2 * old_len - i
        {
            if i < old_len
            {
                new_cells[i] := cells[i];
            } 
            else
            {
                new_cells[i] := 0;
            }

            i := i + 1;
        }

        cells := new_cells;
    }
}
