package org.softwerkskammer.magdeburg.orderedjobs;

public interface IOrderedJobs {

	void register(Character dependentJob, Character independentJob);

	void register(Character job);

	String sort();

}
