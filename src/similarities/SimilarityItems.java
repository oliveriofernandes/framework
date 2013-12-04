package similarities;

import core.Item;

/**
 * @author Oliverio
 */

public class SimilarityItems
{
  public Item activeItem;
  public Item otherItem;
  public double ratingOtherUser;
  public double similarity;

  public SimilarityItems()
  {
  }

  public SimilarityItems(Item activeItem, Item otherItem, double similarity)
  {
    this.activeItem = activeItem;
    this.otherItem = otherItem;
    this.similarity = similarity;
  }

  public SimilarityItems(Item activeItem, Item otherItem) {
    this.activeItem = activeItem;
    this.otherItem = otherItem;
  }

  public void setActiveItem(Item activeItem) {
    this.activeItem = activeItem;
  }

  public void setOtherItem(Item otherItem) {
    this.otherItem = otherItem;
  }

  public void setSimilarity(double similarity) {
    this.similarity = similarity;
  }

  public double getRatingOtherUser() {
    return this.ratingOtherUser;
  }

  public void setRatingOtherUser(double ratingOtherUser) {
    this.ratingOtherUser = ratingOtherUser;
  }
}