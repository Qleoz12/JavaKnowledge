//package com.example.DFA.utils2;
//
//import graph.Edge;
//import graph.Graph;
//import graph.Vertex;
//import graph.doublyLinkedList.DoublyLinkedList;
//import graph.doublyLinkedList.NodeIterator;
//
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.PrintWriter;
//import java.util.Scanner;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
///**
//* Finite Automata
//* Coded by Amir El Bawab
//* Date: 6 January 2015
//* License: MIT License ~ Please read License.txt for more information about the usage of this software
//* */
//public class FiniteAutomata {
//
//	// Attributes
//	private Graph<finiteAutomata.State,Transition> FA;
//	private char vertexPrefix;
//	private Transition[] processTransitions;
//	private finiteAutomata.State initialState;
//
//	/**
//	 * Constructor
//	 * @param vertexPrefix
//	 */
//	public FiniteAutomata(char vertexPrefix) {
//
//		// DiGraph
//		FA = new Graph<>(true);
//		this.vertexPrefix = vertexPrefix;
//	}
//
//	/**
//	 * Add a state
//	 * @param status
//	 * @return added state
//	 */
//	public finiteAutomata.State addState(int status){
//
//		// Only one initial state
//		if(initialState != null && (status == finiteAutomata.State.INITIAL || status == finiteAutomata.State.INITIAL_FINAL))
//			throw new FiniteAutomataException("You cannot have more than one initial state");
//
//		finiteAutomata.State state = new finiteAutomata.State(status);
//		Vertex<finiteAutomata.State, Transition> vertex = FA.addVertex(state);
//		state.setName(String.format("%c%d", vertexPrefix, vertex.getID()));
//		state.setVertex(vertex);
//
//		// Assign the initial state
//		if(state.isInitial())
//			initialState = state;
//
//		// Return the added states
//		return state;
//	}
//
//	/**
//	 * Add a transition
//	 * @param s1
//	 * @param s2
//	 * @param read
//	 * @return Added transition
//	 */
//	public Transition addTransition(finiteAutomata.State s1, finiteAutomata.State s2, char read){
//
//		// Create the transition
//		Transition transition = new Transition(s1,s2,read);
//		Edge<finiteAutomata.State, Transition> edge[] = FA.addEdge(s1.getVertex(), s2.getVertex(),transition,0.0);
//		transition.setEdge(edge[0]);
//
//		// Return added transition
//		return transition;
//	}
//
//	/**
//	 * Remove a state
//	 * @param state
//	 */
//	public void removeState(finiteAutomata.State state){
//		if(state.isInitial())
//			initialState = null;
//		FA.removeVertex(state.getVertex());
//	}
//
//	/**
//	 * Remove a transition
//	 * @param transition
//	 */
//	public void removeTransition(Transition transition){
//		FA.removeEdge(transition.getEdge());
//	}
//
//	/**
//	 * Get the array of states
//	 * @return array of states
//	 */
//	public finiteAutomata.State[] getStates(){
//
//		// Create the states array
//		finiteAutomata.State[] states = new finiteAutomata.State[FA.vertices().size()];
//		NodeIterator<Vertex<finiteAutomata.State, Transition>> iter = FA.vertices();
//		int index=0;
//		while(iter.hasNext())
//			states[index++] = iter.next().getData();
//		return states;
//	}
//
//	/**
//	 * Get the array of transitions
//	 * @return array of transitions
//	 */
//	public Transition[] getTransitions(){
//
//		// Create the transitions array
//		Transition[] transition = new Transition[FA.edges().size()];
//		NodeIterator<Edge<finiteAutomata.State, Transition>> iter = FA.edges();
//		int index=0;
//		while(iter.hasNext())
//			transition[index++] = iter.next().getLabel();
//		return transition;
//	}
//
//	/**
//	 * Get the initial state
//	 * @return initial state
//	 */
//	public finiteAutomata.State getInitialState(){
//		return initialState;
//	}
//
//	/**
//	 * Process a string
//	 * @param input
//	 */
//	public boolean process(String input){
//
//		// An initial state is required to start
//		if(initialState == null)
//			throw new FiniteAutomataException("An initial state is required");
//
//		// Store correct transitions
//		DoublyLinkedList<Transition> list = new DoublyLinkedList<Transition>();
//		boolean process = process_step(input, initialState, list);
//
//		// Store transitions in an array
//		processTransitions = new Transition[list.size()];
//		int index = 0;
//		NodeIterator<Transition> iterT = list.iterator();
//		while(iterT.hasNext())
//			processTransitions[index++] = iterT.next();
//
//		// return process
//		return process;
//	}
//
//	/**
//	 * Process recursive call
//	 * @param input
//	 * @param state
//	 * @param list
//	 * @return boolean
//	 */
//	private boolean process_step(String input, finiteAutomata.State state, DoublyLinkedList<Transition> list){
//
//		// If empty string and final state
//		if(input.length() == 0 && state.isFinal())
//			return true;
//
//		// Iterate on outgoing transitions
//		NodeIterator<Edge<finiteAutomata.State,Transition>> iterE = state.getVertex().getOutEdges();
//		while(iterE.hasNext()){
//			Transition transition = iterE.next().getLabel();
//
//			// Check for lambda transitions
//			if(transition.getRead() == Transition.LAMBDA){
//				if(process_step(input, transition.getEdge().getV2().getData(), list)){
//					list.addFirst(transition);
//					return true;
//				}
//
//			// If not empty string and read match
//			} else if(input.length() > 0 && transition.getRead() == input.charAt(0)){
//				if(process_step(input.substring(1), transition.getEdge().getV2().getData(), list)){
//					list.addFirst(transition);
//					return true;
//				}
//			}
//		}
//
//		// No transition found
//		return false;
//	}
//
//
//	/**
//	 * Remove old initial state (if any). Choose an initial state
//	 * @param stateID
//	 */
//	public void chooseInitialState(finiteAutomata.State state){
//
//		// If the new initial state is the same as the old one, return
//		if(initialState == state)
//			return;
//
//		// If there was an initial state
//		if(initialState != null){
//
//			// Adjust the old
//			if(initialState.getStatus() == finiteAutomata.State.INITIAL_FINAL)
//				initialState.setStatus(finiteAutomata.State.FINAL);
//			else
//				initialState.setStatus(finiteAutomata.State.NORMAL);
//		}
//
//		// Adjust the initial state
//		initialState = state;
//
//		// Adjust the new initial state status
//		if(state.getStatus() == finiteAutomata.State.FINAL)
//			state.setStatus(finiteAutomata.State.INITIAL_FINAL);
//		else
//			state.setStatus(finiteAutomata.State.INITIAL);
//	}
//
//	/**
//	 * Add a final state
//	 * @param state
//	 */
//	public void addFinalState(finiteAutomata.State state){
//		if(state.getStatus() == finiteAutomata.State.INITIAL){
//			state.setStatus(finiteAutomata.State.INITIAL_FINAL);
//		}else if(state.getStatus() == finiteAutomata.State.NORMAL){
//			state.setStatus(finiteAutomata.State.FINAL);
//		}
//	}
//
//	/**
//	 * Remove a final state
//	 * @param state
//	 */
//	public void removeFinalState(finiteAutomata.State state){
//		if(state.getStatus() == finiteAutomata.State.FINAL)
//			state.setStatus(finiteAutomata.State.NORMAL);
//		else if(state.getStatus() == finiteAutomata.State.INITIAL_FINAL)
//			state.setStatus(finiteAutomata.State.INITIAL);
//	}
//
//	/**
//	 * Get last process transitions
//	 * @return array of last process transitions
//	 */
//	public Transition[] getProcessTransitions() {
//		return processTransitions;
//	}
//
//	/**
//	 * Checks if a Finite automata is Deterministic:
//	 * > No lambda transition.
//	 * > Every state has exactly one transition for each alphabet.
//	 * @param alphabet
//	 * @return boolean
//	 */
//	public boolean isDFA(char alphabet[]){
//
//		// Keep track of the alphabet used for each state
//		int alphabetCount[];
//
//		// Iterate on states
//		NodeIterator<Vertex<finiteAutomata.State, Transition>> iterS = FA.vertices();
//		while(iterS.hasNext()){
//			finiteAutomata.State currentState = iterS.next().getData();
//
//			// Reset counter
//			alphabetCount = new int[alphabet.length];
//
//			// Iterate on all transitions for the current state
//			NodeIterator<Edge<finiteAutomata.State,Transition>> iterT = currentState.getVertex().getOutEdges();
//			while(iterT.hasNext()){
//				char read = iterT.next().getLabel().getRead();
//
//				// If lambda transition found
//				if(read == Transition.LAMBDA)
//					return false;
//
//				// Loop on alphabets
//				int i;
//				for(i=0; i<alphabet.length; i++){
//					if(read == alphabet[i]){
//						if(++alphabetCount[i] == 2)
//							return false;
//						else
//							break;
//					}
//				}
//
//				// If a character used but not in the alphabet
//				if(i == alphabet.length)
//					return false;
//			}
//
//			// Verify that all alphabets are used
//			for(int i=0; i<alphabet.length; i++){
//				if(alphabetCount[i] == 0)
//					return false;
//			}
//		}
//
//		// If passes all the tests
//		return true;
//	}
//
//	/**
//	 * Get a list of states and transitions
//	 * @return List of states and transitions
//	 */
//	public String toString(){
//		String output = "States:\n";
//		NodeIterator<Vertex<finiteAutomata.State, Transition>> iterV = FA.vertices();
//		while(iterV.hasNext())
//			output += String.format("%s ", iterV.next().getData());
//
//		output += "\n\nTransitions:\n";
//		NodeIterator<Edge<finiteAutomata.State, Transition>> iterE = FA.edges();
//		while(iterE.hasNext())
//			output += String.format("%s\n", iterE.next().getLabel());
//
//		return output;
//	}
//
//	/////////////////////////// I/O HELPER ///////////////////////////////
//
//	/**
//	 * Parse input to populate the finite automata machine
//	 * @param file
//	 * @return Finite automata machine
//	 * @throws FileNotFoundException
//	 */
//	public static FiniteAutomata inParser(String file) throws FileNotFoundException{
//		FiniteAutomata machine;
//		finiteAutomata.State states[];
//		Scanner scanFile = new Scanner(new File(file));
//		String readLine;
//		Pattern pattern;
//		Matcher matcher;
//		int[] statesStatus;
//		char lambdaChar;
//
//		// Read the machine prefix
//		readLine = scanFile.nextLine();
//		pattern = Pattern.compile("prefix\\s*=\\s*(.)");
//		matcher = pattern.matcher(readLine);
//		matcher.find();
//		machine = new FiniteAutomata(matcher.group(1).charAt(0));
//
//		// Read number of states
//		readLine = scanFile.nextLine();
//		pattern = Pattern.compile("states\\s*=\\s*(\\d+)");
//		matcher = pattern.matcher(readLine);
//		matcher.find();
//		states = new finiteAutomata.State[Integer.parseInt(matcher.group(1))];
//		statesStatus = new int[states.length];
//
//		// Read the special lambda character
//		readLine = scanFile.nextLine();
//		pattern = Pattern.compile("lambda\\s*=\\s*(.)");
//		matcher = pattern.matcher(readLine);
//		matcher.find();
//		lambdaChar = matcher.group(1).charAt(0);
//
//		// Read the initial state
//		readLine = scanFile.nextLine();
//		pattern = Pattern.compile("initial\\s*=\\s*(\\d+)");
//		matcher = pattern.matcher(readLine);
//		matcher.find();
//		statesStatus[Integer.parseInt(matcher.group(1))] = finiteAutomata.State.INITIAL;
//
//		// While there more final states
//		while(!(readLine = scanFile.nextLine()).equals(";") ){
//
//			// Read the final states
//			pattern = Pattern.compile("final\\s*=\\s*(\\d+)");
//			matcher = pattern.matcher(readLine);
//			matcher.find();
//
//			if(statesStatus[Integer.parseInt(matcher.group(1))] == finiteAutomata.State.INITIAL)
//				statesStatus[Integer.parseInt(matcher.group(1))] = finiteAutomata.State.INITIAL_FINAL;
//			else
//				statesStatus[Integer.parseInt(matcher.group(1))] = finiteAutomata.State.FINAL;
//		}
//
//		// Initialize all states
//		for(int i=0; i<states.length; i++){
//			if(statesStatus[i] == 0)
//				states[i] = machine.addState(finiteAutomata.State.NORMAL);
//			else
//				states[i] = machine.addState(statesStatus[i]);
//		}
//
//		// Read all transitions
//		while(!(readLine = scanFile.nextLine()).equals(";") ){
//
//			// Read the final states
//			pattern = Pattern.compile("(\\d+)\\s*,\\s*(\\d+)\\s*:\\s*([^\\s]+)\\s*");
//			matcher = pattern.matcher(readLine);
//			matcher.find();
//
//			char read = matcher.group(3).charAt(0) == lambdaChar ? Transition.LAMBDA: matcher.group(3).charAt(0);
//			machine.addTransition(states[Integer.parseInt(matcher.group(1))], states[Integer.parseInt(matcher.group(2))], read);
//		}
//
//		scanFile.close();
//		return machine;
//	}
//
//	/**
//	 * Export Finite Automata machine to input file
//	 * @param filename
//	 * @throws FileNotFoundException
//	 */
//	public void export(String filename) throws FileNotFoundException{
//
//		// Print writer
//		PrintWriter write = new PrintWriter(filename);
//
//		// Store all vertices of the machine
//		Vertex<finiteAutomata.State, Transition> statesV[] = FA.vertices_array();
//
//		write.println(String.format("prefix = %c", vertexPrefix));
//		write.println(String.format("states = %d", FA.vertices().size()));
//		write.println(String.format("lambda = %c", Transition.LAMBDA));
//		if(initialState != null)
//			write.println(String.format("initial = %d", FA.getIndexOfVertexByID(statesV, initialState.getVertex().getID())));
//		else
//			write.println("initial = <Enter a state id>");
//
//		// Write all final states
//		for(int i=0; i<statesV.length; i++){
//			if(statesV[i].getData().isFinal())
//				write.println(String.format("final = %d", i));
//		}
//
//		write.println(";");
//
//		// Write all transitions
//		for(int i=0; i<statesV.length; i++){
//
//			NodeIterator<Edge<finiteAutomata.State,Transition>> iterE = statesV[i].getOutEdges();
//			while(iterE.hasNext()){
//				Transition transition = iterE.next().getLabel();
//				write.println(String.format("%d,%d : %c", i, FA.getIndexOfVertexByID(statesV, transition.getEdge().getV2().getID()), transition.getRead()));
//			}
//		}
//
//		write.println(";");
//		write.close();
//	}
//}
