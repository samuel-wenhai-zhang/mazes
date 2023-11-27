[![Open in Codespaces](https://classroom.github.com/assets/launch-codespace-7f7980b617ed060a017424585567c406b6ee15c891e84e1186181d67ecf80aa0.svg)](https://classroom.github.com/open-in-codespaces?assignment_repo_id=13051173)
# Solving Mazes


## Description
There are multiple algorithms for solving a maze.  We are going to look at 2 of them:
* depth first (recursive and iterative)
* breadth first (iterative)

The mazes our program will be able to solve will look something like this:
```
#####################
S     #           # #
# # # # ##### ### # #
#   # #     # # #   #
##### ### ### # ### #
#   #   # #   #   # #
# # ### ### ### ### #
# # # # #   #       #
# # # # # ######### #
# #   # # #       # #
# # ### # # ##### ###
# # #   #   #   #   #
# # # ####### # ### #
# # #         # #   #
# ############# # # #
#   #         #   # #
# # # # ### ####### #
# # # # #   #   #   #
# # # # ##### # # ###
# #   #       #     F
#####################
```
Notice the `S` in the top left and the `F` in the bottom right.  These stand for "start" and "finish".  Our mazes will always start in the top left and end in the bottom right (this may be important later!)

The mazes will be randomly generated using a recursive algorithm.  You should take a look at the `Maze` class to see what methods are available to it.  Be sure not to change anything unless I tell you too though (or be ready to revert changes!)

The maze will actually be a 2D array of `Node` objects.  You should take a look at the `Node` class to see what methods are available to it.  Again, don't change anything (or if you do, be sure to revert changes).

## Search Algorithms

### Depth First (recursive)
We will start with a recursive "depth first" search.  
* follow a path down a single direction until either:
    * the end is found, you win
    * dead end, back track to previous turn and try a different direction
* do this recursively until either the end is found or you determine there is no solution to the maze.


### Depth First (iterative)
For this implementation of DFS, we will use a Stack unvisited, and a Set visited.  

The algorithm is as follows:
* Push the starting node onto `unvisited`

Loop as long as `unvisited` is not empty:
* Pop from `unvisited` and set the current node to the popped node
* Check if the current node is the end, if it is, stop
* Check if the current node is in the visited set
  * if it has, move to the next unvisited node
  * otherwise, add it to the visited Set push all of the adjacent nodes to the unvisited stack


### Breadth First 
While DFS will find a path through the maze, it isn't necessarily the shortest path.  This is where the breadth-first search (BFS) comes in.  In DFS, we went one direction until we couldn't go that way anymore, then we tried the next direction.  Whereas in BFS, we check all directions before moving on to the next node.  In other words, we will check all Nodes within 1 of the current node, then all nodes within 2, then all within 3, etc.

We will reuse the visited/unvisited from the DFS with one big difference (and really only one): unvisited will be a **queue**

Here is the algorithm:
* Put the starting node in the unvisited queue.

Loop as long as unvisited is not empty:
* Remove from unvisited and set the current node to be the node that was removed
* Check if the current node is the end, if it is, stop
* Check if the current node is in visited,
    * if it is, move to the next unvisited node
    * otherwise, add it to visited and add all of the adjacent nodes to the unvisited queue


That's it, that's the difference in implementation of a DFS and BFS: DFS uses a Stack, BFS uses a Queue.  As you will see, this causes a MAJOR difference in behavior!

Also, since BFS guarantees a shortest path, we will also keep track of **how** we get to a particular node by marking a node's *parent*.   This will allow us to backtrack through the path and count its length and mark it on the final maze printed.


