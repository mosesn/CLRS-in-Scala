#Algorithms
Implementation of Introduction to Algorithms by CLRS in Scala

##What are you?
I am a collection of algorithms, implemented in Scala.  
I should grow as Moses reworks his way through CLRS, implementing stuff this time.

##Why are you?
[Moses](http://github.com/mnn2104) wants to learn algorithms, and also Scala!

##Next Steps

##Organizational Structure
First directory is the chapter.  
Second directory is the section.  
Third directory is the project.  

Any of the projects can be tested by running sbt test.  

Structure as of 3/2/12  

<pre>
.
|-- 2
|   |-- 1
|   |   |-- InsertionSort
|   |   `-- Search
|   |       |-- BinarySearch
|   |       `-- LinearSearch
|   |-- 2
|   |   `-- SelectionSort
|   |-- 3
|   |   |-- Merge
|   |   |-- MergeSort
|   |   `-- SumExact
|   `-- Hard
|       |-- BubbleSort
|       |-- Inversions
|       `-- Polynomials
|-- 4
|   |-- 1
|   |   `-- MaximumSubarray
|   |       |-- BruteForce
|   |       |-- DivideAndConquer
|   |       |-- LinearOnePass
|   |       `-- LinearThreePass
|   `-- 2
|       |-- SquareNaiveMatrix
|       `-- Strassen
`-- 5
    `-- 3
        `-- Shuffle
</pre>