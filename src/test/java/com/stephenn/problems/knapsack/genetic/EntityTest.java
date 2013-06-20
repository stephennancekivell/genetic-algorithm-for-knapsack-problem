package com.stephenn.problems.knapsack.genetic;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import junit.framework.*;

import static org.mockito.Mockito.*;

public class EntityTest extends TestCase {
	
	float[] prices = {1,1,1};
	float target = 3;
	
	Entity a = new Entity(new float[] {1,1,1}, prices, target);
	Entity b = new Entity(new float[] {2,2,2}, prices, target);
	
	public void testCompair(){
		List<Entity> list = Arrays.asList(a,b);
		
		Collections.sort(list);
		
		assertTrue(Arrays.equals(list.get(1).items, new float[]{1,1,1}));
	}
	
	public void testFitness(){
		assertTrue(a.fitness() == 3.0);
		assertTrue(b.fitness() == -1.0);
	}
	
	public void testMate(){
		Random mockedRandom = mock(Random.class);
		when(mockedRandom.nextBoolean()).thenReturn(true);
		when(mockedRandom.nextInt(10)).thenReturn(0);
		
		Entity child = a.mate(b, mockedRandom);
		
		assertTrue(Arrays.equals(child.items, new float[]{1,1,1}));
	}
	
	public void testMate2(){
		Random mockedRandom = mock(Random.class);
		when(mockedRandom.nextBoolean()).thenReturn(false);
		when(mockedRandom.nextInt(10)).thenReturn(0);
		
		Entity child = a.mate(b, mockedRandom);
		
		assertTrue(Arrays.equals(child.items, new float[]{2,2,2}));
	}
	
	public void testMate3(){
		Random mockedRandom = mock(Random.class);
		when(mockedRandom.nextBoolean()).thenReturn(true);
		when(mockedRandom.nextInt(10)).thenReturn(1);
		when(mockedRandom.nextInt(3)).thenReturn(0);
		
		Entity child = a.mate(b, mockedRandom);
		
		assertTrue(Arrays.equals(child.items, new float[]{0,0,0}));
	}
}
