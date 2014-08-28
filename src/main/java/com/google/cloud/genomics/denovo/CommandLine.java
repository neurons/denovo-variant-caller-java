/*
 *Copyright 2014 Google Inc. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package com.google.cloud.genomics.denovo;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

/**
 * Command line options handler for GenomicsExperiment
 */
class CommandLine {

  CmdLineParser parser;

  @Argument(usage = "The stage of the calling pipeline ; usually stage1 or stage2",
      metaVar = "<stage id>", required = true)
  public String stageId = null;

  @Option(name = "--job_name", metaVar = "<job name>",
      usage = "Name of your job", required = true)
  public String jobName;
  
  @Option(name = "--inference_method", metaVar = "<map|bayes|lrt>",
      usage = "Inference method (map | bayes | lrt)")
  public String inferMethod = "map";

  @Option(name = "--output_file", metaVar = "<file>",
      usage = "File to write results")
  public String outputFileName = null;
  
  @Option(name = "--input_file", metaVar = "<file>",
      usage = "File to read from")
  public String inputFileName = null;
  
  @Option(name = "--client_secrets_filename", metaVar = "<client_secrets_filename>",
      usage = "Path to client_secrets.json")
  public String clientSecretsFilename = "client_secrets.json";

  @Option(name = "--dad_callset_name", metaVar = "<dad_callset_name>",
      usage = "Dad's callset name (usually NA12877)")
  public String dadCallsetName = "NA12877";

  @Option(name = "--mom_callset_name", metaVar = "<mom_callset_name>",
      usage = "Mom's callset name (usually NA12878)")
  public String momCallsetName = "NA12878";

  @Option(name = "--project_id", metaVar = "<project_id>",
      usage = "Project id ( usually 1085016379660L )")
  public long projectId = 1085016379660L;

  @Option(name = "--dataset_id", metaVar = "<dataset_id>",
      usage = "Dataset id ( usually 14004469326575082626 )")
  public String datasetId = "14004469326575082626";
  
  @Option(name = "--child_callset_name", metaVar = "<child_callset_name>",
      usage = "Child's callset name (usually NA12879)")
  public String childCallsetName = "NA12879";
  
  
  @Option(name = "--seq_err_rate", metaVar = "<seq_err_rate>",
      usage = "Specify the sequence error rate (default 1e-2)")
  public double sequenceErrorRate = 1e-2;

  @Option(name = "--denovo_mut_rate", metaVar = "<denovo_mut_rate>",
      usage = "Specify the denovo mutation rate (default 1e-8)")
  public double denovoMutationRate = 1e-8;

  @Option(name = "--lrt_threshold", metaVar = "<lrt_sig_level>",
      usage = "likelihood ratio test significance level (default 1. ;higher the stricter)")
  public double lrtThreshold = 1.0;
  
  @Option(name = "--num_threads", metaVar = "<num_threads>",
      usage = "Specify the number of threads (default 1 ; 1 to 50 suggested)")
  public int numThreads = 1;

  @Option(name = "--debug_level", metaVar = "<debug_level>",
      usage = "specify the debug level (0 for no debug spew)")
  public int debugLevel = 0;

  @Option(name = "--chromosome", metaVar = "<chromosome>",
      usage = "specify the chromosomes to search (specify multiple times for multiple chromsomes)")
  public List<String> chromosomes;

  @Option(name = "--start_position", metaVar = "<start_position>",
      usage = "start position ( usually 1 )")
  public Long startPosition = null; 
  
  @Option(name = "--end_position", metaVar = "<end_position>",
      usage = "end position ( usually set automatically )")
  public Long endPosition = null;

  @Option(name = "--max_variant_results", metaVar = "<max_variant_results>",
      usage = "max variants returned per request (default 10000)")
  public long maxVariantResults = 10000L;
  

  @Option(name = "--max_api_retries", metaVar = "<max_api_retries>",
      usage = "max api retry count (default 5)")
  public int maxApiRetries = 5;

  
  public CommandLine() {
    parser = new CmdLineParser(this);
  }

  public void setArgs(String[] args) throws CmdLineException {
    parser.parseArgument(args);
  }

  public void printHelp(String headline, Appendable out) throws IOException {
    out.append(headline).append("\n").append(getUsage());
  }

  public String getUsage() {
    StringWriter sw = new StringWriter();
    sw.append("Usage: GenomicsExperiment stage_id [flags...]\n");
    parser.printUsage(sw, null);
    return sw.toString();
  }

}
