method Main() {
    print "Hello, Dafny!";
}


function factorial(n: nat): nat
    ensures factorial(n) == if n == 0 then 1 else n * factorial(n - 1)
{
    if n == 0 then 1 else n * factorial(n - 1)
}