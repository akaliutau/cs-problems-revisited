package com.problems.math;

import java.util.ArrayList;
import java.util.List;

/**
 * Solve a given equation and return the value of x in the form of string
 * "x=#value". The equation contains only '+', '-' operation, the variable x and
 * its coefficient.
 * 
 * If there is no solution for the equation, return "No solution".
 * 
 * If there are infinite solutions for the equation, return "Infinite
 * solutions".
 * 
 * If there is exactly one solution for the equation, we ensure that the value
 * of x is an integer.
 * 
 * Example 1: Input: "x+5-3+x=6+x-2" Output: "x=2" 
 * Example 2: Input: "x=x"
 * Output: "Infinite solutions" 
 * Example 3: Input: "2x=x" Output: "x=0"
 * 
 * IDEA:
 * 
 * reduce to l*x=r, => x = r/l
 * 
 */
public class Solution640 {

    // x -> 1, +x -> +1, -x -> -1.
	String coeff(String x) {
		if (x.length() > 1 && x.charAt(x.length() - 2) >= '0' && x.charAt(x.length() - 2) <= '9') {//20x -> 20, etc
			return x.replace("x", "");
		}
		return x.replace("x", "1");
	}

	// splits eq into  list x+5-3+x --> [x, +5, -3, +x]
	List<String> breakIt(String s) {
		List<String> res = new ArrayList<>();
		String r = "";
		char[] sym = s.toCharArray();
		for (int i = 0; i < sym.length; i++) {
			if (sym[i] == '+' || sym[i] == '-') {
				if (r.length() > 0) {
					res.add(r);
				}
				r = "" + sym[i];// start from sign + OR -
			} else
				r += sym[i];
		}
		res.add(r);// add the tail
		return res;
	}

	public String solveEquation(String equation) {
		String[] lr = equation.split("=");
		int lhs = 0;//
		int rhs = 0;
		for (String x : breakIt(lr[0])) {
			if (x.indexOf("x") >= 0) {
				lhs += Integer.parseInt(coeff(x));// x, +10x, -x
			} else
				rhs -= Integer.parseInt(x);// just number
		}
		for (String x : breakIt(lr[1])) {
			if (x.indexOf("x") >= 0)
				lhs -= Integer.parseInt(coeff(x));
			else
				rhs += Integer.parseInt(x);
		}
		if (lhs == 0) {
			if (rhs == 0) {
				return "Infinite solutions";
			} else {
				return "No solution";
			}
		}
		return "x=" + rhs / lhs;
	}



}
