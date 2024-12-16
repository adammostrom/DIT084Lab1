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
    requires cells.Length > 0
    ensures Valid()
    ensures cells.Length == 2 * old(cells.Length)
    ensures read_position == old(read_position)
    ensures write_position == old(write_position)
    ensures forall j : int :: 0 <= j < old(cells.Length) ==> cells[j] == old(cells[j])
    ensures forall j : int :: old(cells.Length) <= j < cells.Length ==> cells[j] == 0
{
    var old_cells := cells;
    var old_len := old_cells.Length;

    var new_cells := new int[2 * old_len];

    var i : int := 0;
    while i < old_len
        invariant 0 <= i <= old_len
        invariant forall j :: 0 <= j < i ==> new_cells[j] == old_cells[j]
        invariant forall j :: i <= j < 2 * old_len ==> new_cells[j] == 0
        invariant new_cells.Length == 2 * old_len
    {
        new_cells[i] := old_cells[i];
        i := i + 1;
    }

    cells := new_cells;
    // No adjustment needed for read_position, write_position, and isFlipped.
}


}
