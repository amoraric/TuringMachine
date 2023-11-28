
# Table of contents
* [TD1: Object Oriented Programming](#td1-object-oriented-programming)
* [TD2: Heritage](#td2-heritage)
* [TD3: Graphical Interfaces: JavaFX](#td3-graphical-interfaces-javafx)
* [TD4: Graphical Interfaces: Events handling](#td3-graphical-interfaces-events-handling)


-----------------------------------------------------------------------------------


# TD1: Object Oriented Programming

## Question 1:
1. The program prints one time the coordinates of a point initiated at (0, 0) and a second time the changed coordinates at (2, 2)
2. *java: class TestPoint is public, should be declared in a file named TestPoint.java*
3. No errors
4. Done

## Question 2:
1. *java: x has private access in TP1.Point*
2. The string that we added + the same as before
3. Nothing happens, we don't process the return of the move method
4. Done

## Question 3:
1. *java: call to this must be first statement in constructor*
2.
    * *java: no suitable constructor found for Point(no arguments)
    constructor TP1.Point.Point(double,double) is not applicable
      (actual and formal argument lists differ in length)
    constructor TP1.Point.Point(TP1.Point) is not applicable
      (actual and formal argument lists differ in length)*
    * Non, on n’obtient plus d’erreur, car java utilise le constructeur par defaut
    * **(0.0, 0.0)
        (2.0, 2.0)**
3. **(10.0, 20.0)
     (12.0, 22.0)**
4. Done

## Question 4:
1. **Circle : [(0.0, 0.0), 5.0]
     Circle : [(2.0, 5.0), 5.0]
     Circle : [(2.0, 5.0), 10.0]**
2. Only one, at the line 46 → *Point p = new Point();*

## Question 5:
1. **Circle : [(0.0, 0.0), 5.0]
     Circle : [(2.0, 5.0), 5.0]
     Circle : [(0.0, 0.0), 5.0]**
2.
    * One instance for Point and another one for Circle
    * p references a point and p2 references the point at the center of the circle
    * p that references the attribute center
3. **Circle : [(0.0, 0.0), 5.0]
     Circle : [(2.0, 5.0), 5.0]
     Circle : [(0.0, 0.0), 5.0]**
4. **Circle : [(0.0, 0.0), 5.0]
     Circle : [(2.0, 5.0), 5.0]
     Circle : [(2.0, 5.0), 5.0]**
5.
    * One instance for Point and another one for Circle
    * p references a point and p2 references a new instance of the point that is the center of the circle
    * it's not p anymore because now when we move p2, p is not modified anymore

## Question 6:
1. **Rectangle : [(0.0, 0.0), (5.0, 3.0)]
     Perimeter : 16.0
     Rectangle : [(2.0, 5.0), (7.0, 8.0)]
     Perimeter : 16.0**
2. 
### Object Diagram
- `Point p1`  
  - x = 0  
  - y = 0

- `Point p2`  
  - x = 5  
  - y = 3

- `Rectangle r`
  - bl -----> p1
  - ur -----> p2

3.
- No, because the perimeter is negative
- **Rectangle : [(0.0, 0.0), (5.0, 3.0)]
    Perimeter : 16.0
    Rectangle : [(12.0, 15.0), (7.0, 8.0)]
    Perimeter : -24.0**
- 16 units
- Becuase the invariant isn't repsected and the bottom left is greater than the upper right
4. **Rectangle : [(0.0, 0.0), (5.0, 3.0)]
     Perimeter : 16.0
     Rectangle : [(2.0, 5.0), (7.0, 8.0)]
     Perimeter : 16.0**
      
      
-----------------------------------------------------------------------------------
      
      
# TD2: Heritage

## Question 1:
1. **(3.0, 6.0) - FF0000FF
      x: 3.0
      color: FF0000FF**
2. The line *System.out.println("color : " + String.format("%08X", p.getColor()));* because this point doesn't have the method “getColor()” anymore. If we remove it we don't get an error anymore.
3. No, because we try to initiate a constructor of Point for an object of type ColoredPoint
4. No, because we try to access a private value of Point
5. *java: cyclic inheritance involving TP2.ColoredPoint*
6. *java: cannot inherit from final TP2.Point*
7. Done

## Question 2:
1. Yes, because all the classes inherit from Object, so we can reference an instance of the class Point with a variable of type Object
2. The same as above
3. Yes, it is defined in the Object class. We can call it on an object of type ColoredPaint, because all the classes inherit from Object
4. Done

## Question 3:
1. *java: call to super must be first statement in constructor*
2. *java: constructor Point in class TP2.Point cannot be applied to given types;* Ca sert a faire appel au constructeur de la classe de laquelle on herite
3. No, because now we're no longer obliged to define the coordinates of x and y because there's a constructor that initiates them at 0 each by default
4. Done

## Question 4:
1. **Constructor of A
     Constructor of B
     Constructor of C**
2. **Constructor of A
     Constructor of B**
3. Yes, it is identical
4. Object() is the default constructor
5. Done

## Question 5: // a repondre
1. **(0.0, 0.0) - not pinned
     (1.0, 1.0) – pinned**
2. Both, we first go through PinnablePoint and then thanks to 'super' we go through Point as well
3. *java: move(double,double) in TP2.PinnablePoint cannot override move(double,double) in TP2.Point overridden method does not throw java.lang.Exception*
4. Yes, *java: unreported exception java.lang.Exception; must be caught or declared to be thrown*
5. Yes, *Exception in thread "main" java.lang.IllegalStateException: Point is pinned, cannot move anymore*
6. Yes
7. Yes
8. *java: move(double,double) in TP2.PinnablePoint cannot override move(double,double) in TP2.Point attempting to assign weaker access privileges; was public*
9. It goes through the move method of the class that it inherits from, here it is Point
10. Done

## Question 6:
1. **(1.0, 1.0)
     (3.0, 5.0) - FF0000FF
     (2.0, 2.0) - not pinned**

## Question 7:
1. That the keyword 'protected' isn't allowed
2. That the keyword public is redundant
3. Done
4. **(1.0, 1.0)
     (3.0, 5.0) - FF0000FF
     (2.0, 2.0) - not pinned**
      
      
-----------------------------------------------------------------------------------
      
      
# TD3: Graphical interfaces: JavaFX

## Question 1:
1. It changes the window's size
2. The window appears without a border. Possible values:
    * *StageStyle.DECORATED - a stage with a solid white background and platform decorations.*
    * *StageStyle.UNDECORATED - a stage with a solid white background and no decorations.*
    * *StageStyle.TRANSPARENT - a stage with a transparent background and no decorations.*
    * *StageStyle.UTILITY - a stage with a solid white background and minimal platform decorations.*
3. The text will take the eventual position depending on the method called

## Question 2:
1. - The first is checked by default and can be either checked or unchecked by clicking
   - The second is indeterminate by default and can be either checked or unchecked by clicking
   - The third one is unchecked by default and can be either checked, unchecked or indeterminate by clicking
2. The first and last checkboxes will not be centered vertically

## Question 3:
1. We don't see the text we type anymore
2. You can't change the text you typed anymore

## Question 4:
When we press the "Print" button, the text we typed is being printed in the command line interface

## Question 5:
The `getChildren()` method returns a collection of nodes that inherit from a parent node that we call the method with.
The interest in using it is to get and work with all the different child nodes of the parent layout

## Question 6:
1. Done
2. Yes, by using the `addAll()` method we can fit the 3 lines in 1
3. The notion of *clip* is important because it determines how the content is shown or masked when it exceeds the limits
of a given container

## Question 7:
1. The objects added to a single cell are arranged in a stack, with the last-added object placed on top
2. The *Password* label will be displayed in the center of it's cell
3. The password TextField will extend to the end of it's column

      
-----------------------------------------------------------------------------------


# TD4: Graphical Interfaces: Events handling

## Question 1:
