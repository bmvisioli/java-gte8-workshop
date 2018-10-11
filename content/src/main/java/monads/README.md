#What are Monads?
If you want to fully understand the concept of Monads I'll direct you to Google but for the sake of this workshop think of Monads as containers of values of certain types.

## `Monad<Type>`

These containers add some context to the values they hold and to access the value it offers some operations:

### Unit
If I have an object of type `A` when I call the `Monad.unit` function passing it as an argument I create a `Monad<A>`
```
String string = "String";
Monad<String> monad = Monad.unit(string);

                               .----------------. 
                              | .--------------. |
                       __     | |      __      | |
     /\                \ \    | |     /  \     | |
    /  \       ______   \ \   | |    / /\ \    | |
   / /\ \     |______|   > >  | |   / ____ \   | |
  / ____ \              / /   | | _/ /    \ \_ | |
 /_/    \_\            /_/    | ||____|  |____|| |
                              | |              | |
                              | '--------------' |
                               '----------------' 
```

### Map (Transform)
If I have a `Monad<A>` when I call `map` with a function `A -> B` I have a `Monad<B>`
```
Integer           value = 1;
Monad<Integer> monadInt = Monad.unit(value);
Monad<String>  monadStr = monad.map(value -> value.toString());

 .----------------.                      .----------------. 
| .--------------. |                    | .--------------. |
| |      __      | |            __      | |   ______     | |
| |     /  \     | |            \ \     | |  |_   _ \    | |
| |    / /\ \    | |    ______   \ \    | |    | |_) |   | |
| |   / ____ \   | |   |______|   > >   | |    |  __'.   | |
| | _/ /    \ \_ | |             / /    | |   _| |__) |  | |
| ||____|  |____|| |            /_/     | |  |_______/   | |
| |              | |                    | |              | |
| '--------------' |                    | '--------------' |
 '----------------'                      '----------------' 

```

### FlatMap (Transform + Combine)
If I have `Monad<A>` when I call `flatMap` on it with a function `A -> Monad<B>` I have a `Monad<B>`

```
Integer           value = 1;
Monad<Integer> monadInt = Monad.unit(value);
Monad<String>  monadStr = monad.flatMap(value -> Monad.unit(value.toString());

// Comparison with Map
Monad<Monad<String> monadMon = monad.map(value -> Monad.unit(value.toString());

 .----------------.               .-----------------.                   .-----------------. 
| .--------------. |             | .--------------. |                   | .--------------. |
| |      __      | |      _      | |   ______     | |           __      | |   ______     | |
| |     /  \     | |     | |     | |   |_   _ \   | |           \ \     | |  |_   _ \    | |
| |    / /\ \    | |   __| |__   | |     | |_) |  | |   ______   \ \    | |    | |_) |   | |
| |   / ____ \   | |  |__  ___|  | |     |  __'.  | |  |______|   > >   | |    |  __'.   | |
| | _/ /    \ \_ | |     | |     | |    _| |__) | | |            / /    | |   _| |__) |  | |
| ||____|  |____|| |     |_|     | |   |_______/  | |           /_/     | |  |_______/   | |
| |              | |             | |              | |                   | |              | |
| '--------------' |             | '--------------' |                   | '--------------' |
 '----------------'               '-----------------'                    '-----------------' 
```

## Examples in Java

* Stream (0 or more values)
```
Stream.of(T)
Stream<T>.map(Function<T, Z>)
Stream<T>.flatMap(Function<T, Stream<Z>>)
```
* Optional (0 or 1 values)
```
Optional.of(T)
Optional<T>.map(Function<T, Z>)
Optional<T>.flatMap(Function<T, Optional<Z>>)
```
* CompletableFuture (1 value eventually)
```
CompletableFuture.completed(T)
CompletableFuture<T>.map(Function<T, Z>)
CompletableFuture<T>.thenCompose(Function<T, CompletableFuture<Z>>)
```