package com.mr;

import java.util.Random;

public class RandomSelector {
	static Random random = new Random();
	private double[] props;
	private double sum = 0.0;
	
	public RandomSelector(double[] props) {
		super();
		this.props = props;
		for (double s : props) {
			sum += s;
		}
	}

	public int select() {
		double val = random.nextDouble() * sum;
		double curSum = 0.0;
		for (int i = 0; i < props.length; ++i) {
			curSum += props[i];
			if (val < curSum) {
				return i;
			}
		}
		return -1;
	}
	
	public static void main(String[] args) {
		double [] props = new double[]{1, 0.22, 0.4};
		RandomSelector randomSelector = new RandomSelector(props);
		
		double [] counter = new double[]{0.0, 0.0, 0.0};
		for (int i = 0; i < 10000000; ++i) {
			counter[randomSelector.select()] += 1.0;
		}
		for (double s : counter) {
			System.out.println(s/10000000.0 * 1.62);
		}
		
	}
	
}
