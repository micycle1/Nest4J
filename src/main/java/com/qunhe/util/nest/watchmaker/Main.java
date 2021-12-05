package com.qunhe.util.nest.watchmaker;

import java.util.List;
import java.util.Random;

import org.uncommons.watchmaker.framework.EvolutionEngine;
import org.uncommons.watchmaker.framework.EvolutionaryOperator;
import org.uncommons.watchmaker.framework.FitnessEvaluator;
import org.uncommons.watchmaker.framework.GenerationalEvolutionEngine;
import org.uncommons.watchmaker.framework.SelectionStrategy;
import org.uncommons.watchmaker.framework.selection.RankSelection;

import com.qunhe.util.nest.algorithm.Individual;

public class Main {

	public static void main(String[] args) {
		
		CandidateFactoryNext4j candidateFactory = new CandidateFactoryNext4j();
		
		EvolutionaryOperator<Individual> evolutionaryOperator = new EvolutionaryOperator<Individual>() {

			@Override
			public List<Individual> apply(List<Individual> arg0, Random arg1) {
				// TODO Auto-generated method stub
				return null;
			}
		};
		FitnessEvaluator<? super Individual> fitnessEvaluator = new FitnessEvaluator<Individual>() {

			@Override
			public double getFitness(Individual arg0, List<? extends Individual> arg1) {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public boolean isNatural() {
				// TODO Auto-generated method stub
				return false;
			}
		};
		SelectionStrategy<? super Individual> selectionStrategy = new RankSelection();
		Random rng = new Random();
		EvolutionEngine<Individual> engine = new GenerationalEvolutionEngine<>(candidateFactory,
				evolutionaryOperator, fitnessEvaluator, selectionStrategy, rng);

	}

}