# Utilities

All utilities are free to use for all users and consists of:
### Collections

* ###### Liszt
  Implements a List of element E in an append way of adding elements.
  It also implements the interface ICollectionUtility, which contains extra useful methods.
  An extra detail is that this class also uses a map, which means that
  the approach of getting also can be done through the map, this also
  means, that they will be saved doing add and removed at remove.
  Index can both start at 0 or 1, every method starting with an uppercase
  letter starts with 1 instead 0 in the parameters.

* ###### Seszt
  Implements a Set of element E in an append way of adding elements.
  It also implements the interface ICollectionUtility, which contains extra useful methods.
  An extra detail is that this class also uses a map, which means that
  the approach of getting also can be done through the map, this also
  means, that they will be saved doing add and removed at remove.
  Index can both start at 0 or 1, every method starting with an uppercase
  letter starts with 1 instead 0 in the parameters.

### Console

* ###### Printer
  Will handle printing of statements to the console.
  Contains three different options for modes, default will use a normal println,
  the others have a border around each print, noir mode is without colour
  and high contrast mode is with colour of high contrast.
  Is intended to log each print, but isn't implemented yet.

### Parameters

* ###### Plato
  A utility class, that behaves as a boolean, but with extra features.
  Is named after the philosopher Plato ironically, because of Plato's duality theory.
  Instead of just being true or false, it has more values, such as undefined.
  Uses an enum for identifying those values.
  Can also be null, since it's a class object.