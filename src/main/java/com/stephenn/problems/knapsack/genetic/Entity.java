package com.stephenn.problems.knapsack.genetic;

import java.util.*;

public class Entity implements Comparable<Entity> {
	float[] items;
	float[] prices;
	float target;
	
	public Entity(float[] items, float[] prices, float target){
		this.items = items;
		this.prices = prices;
		this.target = target;
	}
	
	public float fitness(){
		float cost = 0;
		for (int i=0; i < items.length; i++){
			cost += (prices[i] * items[i]);
		}
		
		if (cost <= target){
			return cost;
		} else {
			return -1;
		}
	}

	@Override
	public int compareTo(Entity other) {
		return (int) (this.fitness() - other.fitness());
	}
	
	public Entity bread(Entity other){
		Random rand = new Random();
		float[] child = new float[this.items.length];
		for(int i=0; i< this.items.length; i++){
			if (rand.nextBoolean()){
				child[i] = this.items[i];
			} else {
				child[i] = other.items[i];
			}
			if (rand.nextInt(10) == 1){
				child[i] += (-1 + rand.nextInt(3)); // -1, 0, 1
			}
		}
		
		return new Entity(child, this.prices, this.target);
	}
}
