package control;

import similaritiesImpl.SimilarityContext;
import similaritiesImpl.SimilarityStrategy;

public class Comparision
{
  String algorithtm;
  String similarity;
  Class<? extends Object> clsAlgorithtm;
  Class<? extends SimilarityStrategy> clsSimilarity;
  Class<? extends SimilarityContext> clsContext;

  public Class<? extends SimilarityContext> getClsContext()
  {
    return this.clsContext;
  }

  public void setClsContext(Class<? extends SimilarityContext> clsContext) {
    this.clsContext = clsContext;
  }

  public Class<? extends Object> getClsAlgorithtm() {
    return this.clsAlgorithtm;
  }

  public void setClsAlgorithtm(Class<? extends Object> clsAlgorithtm) {
    this.clsAlgorithtm = clsAlgorithtm;
  }

  public Class<? extends SimilarityStrategy> getClsSimilarity() {
    return this.clsSimilarity;
  }

  public void setClsSimilarity(Class<? extends SimilarityStrategy> clsSimilarity) {
    this.clsSimilarity = clsSimilarity;
  }

  public String getAlgorithtm() {
    return this.algorithtm;
  }

  public void setAlgorithtm(String algorithtm) {
    this.algorithtm = algorithtm;
  }

  public String getSimilarity() {
    return this.similarity;
  }

  public void setSimilarity(String similarity) {
    this.similarity = similarity;
  }

  public int hashCode()
  {
    int prime = 31;
    int result = 1;
    result = 31 * result + (this.algorithtm == null ? 0 : this.algorithtm.hashCode());
    result = 31 * result + (this.clsAlgorithtm == null ? 0 : this.clsAlgorithtm.hashCode());
    result = 31 * result + (this.clsContext == null ? 0 : this.clsContext.hashCode());
    result = 31 * result + (this.clsSimilarity == null ? 0 : this.clsSimilarity.hashCode());
    result = 31 * result + (this.similarity == null ? 0 : this.similarity.hashCode());
    return result;
  }

  public boolean equals(Object obj)
  {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Comparision other = (Comparision)obj;
    if (this.algorithtm == null) {
      if (other.algorithtm != null)
        return false;
    } else if (!this.algorithtm.equals(other.algorithtm))
      return false;
    if (this.clsAlgorithtm == null) {
      if (other.clsAlgorithtm != null)
        return false;
    } else if (!this.clsAlgorithtm.equals(other.clsAlgorithtm))
      return false;
    if (this.clsContext == null) {
      if (other.clsContext != null)
        return false;
    } else if (!this.clsContext.equals(other.clsContext))
      return false;
    if (this.clsSimilarity == null) {
      if (other.clsSimilarity != null)
        return false;
    } else if (!this.clsSimilarity.equals(other.clsSimilarity))
      return false;
    if (this.similarity == null) {
      if (other.similarity != null)
        return false;
    } else if (!this.similarity.equals(other.similarity))
      return false;
    return true;
  }
}