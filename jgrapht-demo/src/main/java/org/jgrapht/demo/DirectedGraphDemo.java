/* 
 * Copyright (C) 2008-2018,  Minh Van Nguyen &lt; nguyenminh2@gmail.com&gt;
 *
 * JGraphT : a free Java graph-theory library
 *
 * DirectedGraphDemo.java --- demonstration of operations on directed graphs
 * This program and the accompanying materials are dual-licensed under
 * either
 *
 * (a) the terms of the GNU Lesser General Public License version 2.1
 * as published by the Free Software Foundation, or (at your option) any
 * later version.
 *
 * or (per the licensee's choosing)
 *
 * (b) the terms of the Eclipse Public License v1.0 as published by
 * the Eclipse Foundation.
 * This program is free software; you can redistribute it and/or  modify
 * it under the terms of the GNU General Public License as published  by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA 02111-1307, USA.
 */

 package org.jgrapht.demo;
 
 
import java.util.*;

import org.jgrapht.*;
import org.jgrapht.alg.connectivity.*;
import org.jgrapht.alg.interfaces.*;
import org.jgrapht.alg.interfaces.ShortestPathAlgorithm.*;
import org.jgrapht.alg.shortestpath.*;
import org.jgrapht.graph.*;

/**
 * This class demonstrates some of the operations that can be performed on directed graphs. After
 * constructing a basic directed graph, it computes all the strongly connected components of this
 * graph. It then finds the shortest path from one vertex to another using Dijkstra's shortest path
 * algorithm. The sample code should help to clarify to users of JGraphT that the class
 * org.jgrapht.alg.shortestpath.DijkstraShortestPath can be used to find shortest paths within
 * directed graphs.
 *
 * @author Minh Van Nguyen
 * @since 2008-01-17
 */
public class DirectedGraphDemo
{
    /**
     * The starting point for the demo.
     *
     * @param args ignored.
     */
    
    public static void main(String args[])
    {
        // constructs a directed graph with the specified vertices and edges
        DefaultDirectedGraph<String, DefaultEdge> directedGraph =
            new DefaultDirectedGraph<String, DefaultEdge>(DefaultEdge.class);
        directedGraph.addVertex("a");
        directedGraph.addVertex("b");
        directedGraph.addVertex("c");
        directedGraph.addVertex("d");
        directedGraph.addVertex("e");
        directedGraph.addVertex("f");
        directedGraph.addVertex("g");
        directedGraph.addVertex("h");
        directedGraph.addVertex("i");
        directedGraph.addEdge("a", "b");
        directedGraph.addEdge("b", "d");
        directedGraph.addEdge("d", "c");
        directedGraph.addEdge("c", "a");
        directedGraph.addEdge("e", "d");
        directedGraph.addEdge("e", "f");
        directedGraph.addEdge("f", "g");
        directedGraph.addEdge("g", "e");
        directedGraph.addEdge("h", "e");
        directedGraph.addEdge("i", "h");

        // computes all the strongly connected components of the directed graph
        StrongConnectivityAlgorithm<String, DefaultEdge> scAlg =
            new KosarajuStrongConnectivityInspector<>(directedGraph);
        List<Graph<String, DefaultEdge>> stronglyConnectedSubgraphs =
            scAlg.getStronglyConnectedComponents();

        // prints the strongly connected components
        System.out.println("Strongly connected components:");
        for (int i = 0; i < stronglyConnectedSubgraphs.size(); i++) {
            System.out.println(stronglyConnectedSubgraphs.get(i));
        }
        System.out.println();

        // Prints the shortest path from vertex i to vertex c. This certainly
        // exists for our particular directed graph.
        System.out.println("Shortest path from i to c:");
        DijkstraShortestPath<String, DefaultEdge> dijkstraAlg =
            new DijkstraShortestPath<>(directedGraph);
        SingleSourcePaths<String, DefaultEdge> iPaths = dijkstraAlg.getPaths("i");
        System.out.println(iPaths.getPath("c") + "\n");

        // Prints the shortest path from vertex c to vertex i. This path does
        // NOT exist for our particular directed graph. Hence the path is
        // empty and the variable "path"; must be null.
        System.out.println("Shortest path from c to i:");
        SingleSourcePaths<String, DefaultEdge> cPaths = dijkstraAlg.getPaths("c");
        System.out.println(cPaths.getPath("i"));
    }
}

