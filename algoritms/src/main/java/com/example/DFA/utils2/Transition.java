//package com.example.DFA.utils2;
//import graph.Edge;
//
///**
//* Finite Automata
//* Coded by Amir El Bawab
//* Date: 6 January 2015
//* License: MIT License ~ Please read License.txt for more information about the usage of this software
//* */
//public class Transition {
//
//	// Attributes
//	private char read;
//
//	// Edge that stores this Transition
//	private Edge<State, Transition> edge;
//
//	// Reserved constants
//	public final static char LAMBDA = 'λ';
//
//	/**
//	 * Constructor
//	 * @param s1
//	 * @param s2
//	 * @param read
//	 */
//	protected Transition(State s1, State s2, char read) {
//
//		// Assign attributes
//		setRead(read);
//	}
//
//	/**
//	 * Get the read
//	 * @return read
//	 */
//	public char getRead() {
//		return read;
//	}
//
//	/**
//	 * Set the read
//	 * @param read
//	 */
//	public void setRead(char read){
//		this.read = read;
//	}
//
//	/**
//	 * Get the edge that stores this transition
//	 * @return
//	 */
//	protected Edge<State, Transition> getEdge() {
//		return edge;
//	}
//
//	/**
//	 * Set the edge that stores this transition
//	 * @param edge
//	 */
//	protected void setEdge(Edge<State, Transition> edge) {
//		this.edge = edge;
//	}
//
//	/**
//	 * Get From state
//	 * @return From State
//	 */
//	public State getFromState(){
//		return edge.getV1().getData();
//	}
//
//	/**
//	 * Get To state
//	 * @return To State
//	 */
//	public State getToState(){
//		return edge.getV2().getData();
//	}
//
//	/**
//	 * To string of a transition
//	 */
//	public String toString(){
//		return String.format("[%s, %s : %c]", edge.getV1().getData(), edge.getV2().getData(), read);
//	}
//}
