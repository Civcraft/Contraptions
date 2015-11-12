package com.programmerdan.minecraft.contraptions;

public enum EvictionStrategy {
	lru, // Least recently used (oldest)
	mru, // Most recently used (newest)
	random, // random eviction
	lfu, // Least frequently used
	mfu, // Most frequently used
	smallest, // Smallest (not always applicable)
	largest // Largest (not always applicable)
}
