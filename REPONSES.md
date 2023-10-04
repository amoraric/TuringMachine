
# Table of contents
* [TD1: Orienté objet](#td1:-orienté-objet)
* [TD2: Héritage](#td2:-héritage)


-----------------------------------------------------------------------------------


#TD1: Orienté objet

##Question 1:
1. Le programme affiche une premiere fois les coordonnees d’un point initie a (0, 0) et une deuxieme fois les coordonnees changees a (2, 2)
2. `java: class TestPoint is public, should be declared in a file named TestPoint.java`
3. Pas d’erreur
4. C’est fait

##Question 2:
1. `java: x has private access in TP1.Point`
2. Le string qu’on a rajoute + la meme chose qu’avant
3. Rien ne se passe, on ne traite pas le retour de la methode move
4. C’est fait

##Question 3:
1. `java: call to this must be first statement in constructor`
2.
    - ```java: no suitable constructor found for Point(no arguments)
    constructor TP1.Point.Point(double,double) is not applicable
      (actual and formal argument lists differ in length)
    constructor TP1.Point.Point(TP1.Point) is not applicable
      (actual and formal argument lists differ in length)```
    - Non, on n’obtient plus d’erreur, car java utilise le constructeur par defaut
    - ```(0.0, 0.0)
         (2.0, 2.0)```
3. ```(10.0, 20.0)
      (12.0, 22.0)```
4. C’est fait

##Question 4:
1. ```Circle : [(0.0, 0.0), 5.0]
      Circle : [(2.0, 5.0), 5.0]
      Circle : [(2.0, 5.0), 10.0]```
2. Une seule, a la ligne 46 → `Point p = new Point();`

##Question 5:
1. ```Circle : [(0.0, 0.0), 5.0]
      Circle : [(2.0, 5.0), 5.0]
      Circle : [(0.0, 0.0), 5.0]```
2.
- Une instance pour Point et une autre pour Circle
- p reference un point et p2 reference le point qui est le centre du cercle
- c’est p qui reference l’attribut centre
3. ```Circle : [(0.0, 0.0), 5.0]
      Circle : [(2.0, 5.0), 5.0]
      Circle : [(0.0, 0.0), 5.0]```
4. ```Circle : [(0.0, 0.0), 5.0]
      Circle : [(2.0, 5.0), 5.0]
      Circle : [(2.0, 5.0), 5.0]```
5.
- Une instance pour Point et une autre pour Circle
- p reference un point et p2 reference une nouvelle instance du point qui est le centre du cercle
- ce n’est plus p car maintenant quand on fait un move sur p2 p n’est pas modifie

##Question 6:
1. ```Rectangle : [(0.0, 0.0), (5.0, 3.0)]
      Perimeter : 16.0
      Rectangle : [(2.0, 5.0), (7.0, 8.0)]
      Perimeter : 16.0```
2. 
3.
- Non, car le perimetre est negatif
- ```Rectangle : [(0.0, 0.0), (5.0, 3.0)]
     Perimeter : 16.0
     Rectangle : [(12.0, 15.0), (7.0, 8.0)]
     Perimeter : -24.0```
- Toujours 16 unites
- Car l’invariant n’est pas respecte et le bottom left est plus grand que le upper right
4. ```Rectangle : [(0.0, 0.0), (5.0, 3.0)]
      Perimeter : 16.0
      Rectangle : [(2.0, 5.0), (7.0, 8.0)]
      Perimeter : 16.0```
      
      
-----------------------------------------------------------------------------------
      
      
#TD2: Héritage

##Question 1:
1. ```(3.0, 6.0) - FF0000FF
      x: 3.0
      color: FF0000FF```
2. La ligne `System.out.println("color : " + String.format("%08X", p.getColor()));` car ce point n’a plus de methode “getColor()”. Si on la supprime on n’a plus d’erreur.
3. Non, car on essaye d’initier un constructeur de Point pour un objet de type ColoredPoint
4. Non, car on essaye d’acceder a une valeur privee de Point
5. `java: cyclic inheritance involving TP2.ColoredPoint`
6. `java: cannot inherit from final TP2.Point`
7. C’est fait

##Question 2:
1. Oui, parce que toutes les classes heritent d’Object, donc on peut referencer une instance de la classe Point avec une variable de type Object
2. Egalement
3. Oui, elle est definie dans la classe Object. On peut l’appeler sur un objet de type ColoredPoint, car toute classe herite d’Object
4. C’est fait

##Question 3:
1. `java: call to super must be first statement in constructor`
2. `java: constructor Point in class TP2.Point cannot be applied to given types;` Ca sert a faire appel au constructeur de la classe de laquelle on herite
3. Non, car maintenant on n’est plus oblige de definir les coordonnees x, y car il y a un constructeur qui les initie a 0 par defaut
4. C’est fait

##Question 4:
1. ```Constructor of A
      Constructor of B
      Constructor of C```
2. ```Constructor of A
      Constructor of B```
3. Oui, il est bien identique
4. 
5. C’est fait

##Question 5: // a repondre
1. ```(0.0, 0.0) - not pinned
      (1.0, 1.0) – pinned```
2. Les deux, on passe d’abord par PinnablePoint et puis grace au super on passe par Point aussi
3. `java: move(double,double) in TP2.PinnablePoint cannot override move(double,double) in TP2.Point overridden method does not throw java.lang.Exception`
4. Oui, `java: unreported exception java.lang.Exception; must be caught or declared to be thrown`
5. Oui, `java: unreported exception java.lang.Exception; must be caught or declared to be thrown`
6. Oui
7. 
8. `java: move(double,double) in TP2.PinnablePoint cannot override move(double,double) in TP2.Point attempting to assign weaker access privileges; was public`
9. Il va dans la methode move de la classe de laquelle on herite, ici c’est Point
10. C’est fait

##Question 6:
1. ```(1.0, 1.0)
      (3.0, 5.0) - FF0000FF
      (2.0, 2.0) - not pinned```

##Question 7:
1. 
2. 
3. C’est  fait
4. ```(1.0, 1.0)
      (3.0, 5.0) - FF0000FF
      (2.0, 2.0) - not pinned```
