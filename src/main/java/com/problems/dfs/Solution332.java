package com.problems.dfs;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * Given a list of airline tickets represented by pairs of departure and arrival
 * airports [from, to], reconstruct the itinerary in order. All of the tickets
 * belong to a man who departs from JFK. Thus, the itinerary must begin with
 * JFK. Note: If there are multiple valid itineraries, you should return the
 * itinerary that has the smallest lexical order when read as a single string.
 * For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than
 * ["JFK", "LGB"]. All airports are represented by three capital letters (IATA
 * code). You may assume all tickets form at least one valid itinerary. One must
 * use all the tickets once and only once. 
 * 
 * 
 * Example 1: Input: [["MUC", "LHR"],
 * ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]] Output: ["JFK", "MUC", "LHR",
 * "SFO", "SJC"]
 */
public class Solution332 {

    Map<String, List<Flight>> flights;

    static class Flight {
        List<String> ticket;
        boolean done = false;

        public Flight(List<String> ticket) {
            this.ticket = ticket;
        }

        public String getSrc() {
            return ticket.get(0);
        }

        public String getDest() {
            return ticket.get(1);
        }

    }

    boolean fly(String src, Stack<String> path, int len) {
        int curLen = path.size();
        if (!flights.containsKey(src)) {
            return curLen == len;
        }
        if (curLen == len) {
            return true;
        }
        List<Flight> nextDest = flights.get(src);
        for (Flight flight : nextDest) {
            if (flight.done) {
                continue;
            }
            path.add(flight.getDest());
            flight.done = true;
            if (fly(flight.getDest(), path, len)) {
                return true;
            }
            path.pop();
            flight.done = false;
        }
        return false;
    }

    public List<String> findItinerary(List<List<String>> tickets) {
        flights = new HashMap<>();
        for (List<String> tk : tickets) {
            flights.computeIfAbsent(tk.get(0), key -> new ArrayList<>()).add(new Flight(tk));
        }
        Comparator<Flight> byDest = (o, p) -> o.getDest().compareTo(p.getDest());
        for (String src : flights.keySet()) {
            flights.get(src).sort(byDest);
        }

        Stack<String> path = new Stack<>();
        path.add("JFK");
        fly("JFK", path, tickets.size() + 1);
        return path;

    }

    public static void main(String[] arg) {

        System.out.println();

    }

}
