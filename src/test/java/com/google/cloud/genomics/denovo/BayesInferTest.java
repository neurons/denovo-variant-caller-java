package com.google.cloud.genomics.denovo;

import static com.google.cloud.genomics.denovo.DenovoUtil.TrioIndividual.CHILD;
import static com.google.cloud.genomics.denovo.DenovoUtil.TrioIndividual.DAD;
import static com.google.cloud.genomics.denovo.DenovoUtil.TrioIndividual.MOM;

import com.google.cloud.genomics.denovo.DenovoUtil.Allele;
import com.google.cloud.genomics.denovo.DenovoUtil.TrioIndividual;

import java.util.HashMap;
import java.util.Map;


/**
 * TODO: Insert description here. (generated by smoitra)
 */
public abstract class BayesInferTest extends DenovoTest {

  static BayesInfer bayesInferrer = new BayesInfer(1e-2, 1e-8);

  static ReadSummary createAlmostSameReadSummary() {
    Map<Allele, Integer> baseCount = new HashMap<>();
    baseCount.put(Allele.A,38);
    baseCount.put(Allele.C,2);
    baseCount.put(Allele.G,3);
    return new ReadSummary().setCount(baseCount);
  }

  static ReadSummary createSameReadSummary() {
    Map<Allele, Integer> baseCount = new HashMap<>();
    baseCount.put(Allele.A,40);
    return new ReadSummary().setCount(baseCount);
  }
  
  static Map<TrioIndividual, ReadSummary> createMapReadSummary(ReadSummary dad, ReadSummary mom,
      ReadSummary child) {
    Map<TrioIndividual, ReadSummary> readSummaryMap = new HashMap<>();
    readSummaryMap.put(DAD, dad);
    readSummaryMap.put(MOM, mom);
    readSummaryMap.put(CHILD, child);
    return readSummaryMap;
  }

}
