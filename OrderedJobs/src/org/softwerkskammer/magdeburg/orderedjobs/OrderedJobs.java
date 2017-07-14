package org.softwerkskammer.magdeburg.orderedjobs;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class OrderedJobs implements IOrderedJobs {

	private Map<Character, Set<Character>> jobs = new HashMap<>();	

	@Override
	public void register(Character abhaengig, Character unabhaengig) {
		if (jobs.getOrDefault(unabhaengig, Collections.emptySet()).contains(abhaengig)) {
			throw new IllegalArgumentException();
		}		

		if (!jobs.containsKey(unabhaengig)) {
			jobs.put(unabhaengig, new HashSet<>());
		}
		
		if (abhaengig != null) {
			if (!jobs.containsKey(abhaengig)) {
				jobs.put(abhaengig, new HashSet<>());
			}
			jobs.get(abhaengig).add(unabhaengig);
		}
	}

	@Override
	public void register(Character job) {
		register(null, job);
	}

	@Override
	public String sort() {
		String s = "";
		for (Character c : jobs.keySet()) {
			s = collectRecursivly(c, s);
		}
		return s;
	}
		
	private String collectRecursivly(Character currentJob, final String orderedJobs) {
		if (orderedJobs.contains(currentJob.toString())) {
			return orderedJobs;
		}
		
		Set<Character> dependendJobs = jobs.getOrDefault(currentJob, Collections.emptySet());
		if (dependendJobs.isEmpty()) {
			return orderedJobs + currentJob;
		}
		
		return dependendJobs.stream()
				.map(c -> collectRecursivly(c, orderedJobs))
				.collect(Collectors.joining()) + currentJob;
	}

}