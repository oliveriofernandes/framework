package control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import util.Position;

public class StorageSettings
{
  private static StorageSettings instance;

  @Position(index=1, labelName="Type Matrix")
  public String matrixType;

  @Position(index=2, labelName="Test option")
  public String testOption;
  public int numberComparaties;
  public boolean isNewProject;
  public String dataSetPath;
  public static int numMaxComparisons;
  public static int numMinComparisons = 2;
  public int kValue;

  @Position(index=3, labelName="Percent train")
  public String percentTrain;

  @Position(index=4, labelName="Percent test")
  public String percentTest;

  @Position(index=5)
  public Collection<Comparision> comparisions;

  @Position(index=6, labelName="Experiment strategy")
  public String experimentStrategy;

  @Position(index=7, labelName="Evaluation metrics")
  public Collection<EvaluationMetric> evaluationMetrics;

  public Collection<EvaluationMetric> getEvaluationMetrics()
  {
    return this.evaluationMetrics;
  }

  public void setEvaluationMetrics(Collection<EvaluationMetric> evaluationMetrics) {
    this.evaluationMetrics = evaluationMetrics;
  }

  public int getkValue() {
    return this.kValue;
  }

  public void setkValue(int kValue) {
    this.kValue = kValue;
  }

  private StorageSettings() {
    this.comparisions = new ArrayList();
    this.evaluationMetrics = new ArrayList();
    this.isNewProject = true;
   /// try
   // {
      numMaxComparisons = 4;//ListClasses.getClassNamesByPkg("memoryBasedMethods.similarities", true, false).length * 
        //ListClasses.getClassNamesByPkg("memoryBasedMethods.predictions", true, false).length;
    
   //   System.out.println(numMaxComparisons);
    
   // }
  //  catch (ClassNotFoundException e) {
   //   e.printStackTrace();
   // }
   // catch (IOException e) {
   //   e.printStackTrace();
   // }
  }

  public static StorageSettings getInstance() {
    if (instance == null) {
      instance = new StorageSettings();
    }
    return instance;
  }

  public Collection<Comparision> getComparisions() {
    return this.comparisions;
  }

  public void setComparisions(Collection<Comparision> comparisions) {
    this.comparisions = comparisions;
  }

  public String getExperimentStrategy() {
    return this.experimentStrategy;
  }

  public void setExperimentStrategy(String experimentStrategy) {
    this.experimentStrategy = experimentStrategy;
  }

  public String getPercentTest() {
    return this.percentTest;
  }

  public void setPercentTest(String percentTest) {
    this.percentTest = percentTest;
  }

  public String getPercentTrain() {
    return this.percentTrain;
  }

  public void setPercentTrain(String percentTrain) {
    this.percentTrain = percentTrain;
  }

  public void setMatrixType(String parameter) {
    this.matrixType = parameter;
  }

  public void setTestOption(String parameter) {
    this.testOption = parameter;
  }

  public void setNumberComparaties(int parameter) {
    this.numberComparaties = parameter;
  }

  public void setIsNewProject(boolean parameter) {
    this.isNewProject = parameter;
  }

  public void setDataSetPath(String path) {
    this.dataSetPath = path;
  }
}