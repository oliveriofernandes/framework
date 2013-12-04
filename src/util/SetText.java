package util;

import java.lang.reflect.Field;
import java.util.Collection;

import javax.swing.JTextArea;

import control.Comparision;
import control.EvaluationMetric;
import control.StorageSettings;

public final class SetText
  implements Runnable
{
  JTextArea textArea;
  StorageSettings storageSettings;
  int indexNumber;
  StringBuilder stringBuilder;

  public SetText(JTextArea tA)
  {
    this.textArea = tA;
    this.storageSettings = StorageSettings.getInstance();
  }

  public SetText(JTextArea tA, int num) {
    this.textArea = tA;
    this.storageSettings = StorageSettings.getInstance();
    this.indexNumber = num;
    this.stringBuilder = new StringBuilder();
  }

  public void run()
  {
    Class klass = this.storageSettings.getClass();
    Field[] fieldArray = klass.getDeclaredFields();
    Field field = null;
    try
    {
      for (int i = 0; i < fieldArray.length; i++) {
        if ((fieldArray[i].isAnnotationPresent(Position.class)) && 
          (((Position)fieldArray[i].getAnnotation(Position.class)).index() == this.indexNumber))
          field = klass.getField(fieldArray[i].getName());
      }
    }
    catch (SecurityException e)
    {
      e.printStackTrace();
    } catch (NoSuchFieldException e) {
      e.printStackTrace();
    }

    if (field != null)
      try {
        for (int i = 1; i <= this.indexNumber; i++) {
          fillStringBuilder(this.storageSettings, this.stringBuilder);
          this.textArea.setText(this.stringBuilder.toString());
        }
      }
      catch (IllegalArgumentException e) {
        e.printStackTrace();
      } catch (IllegalAccessException e) {
        e.printStackTrace();
      }
  }

  public void fillStringBuilder(StorageSettings storageSettings, StringBuilder stringBuilder)
    throws IllegalArgumentException, IllegalAccessException
  {
    Class klass = storageSettings.getClass();
    stringBuilder.delete(0, stringBuilder.length());

    for (Field field : klass.getDeclaredFields())
      if ((field.isAnnotationPresent(Position.class)) && (((Position)field.getAnnotation(Position.class)).index() <= this.indexNumber)) {
        if (((Position)field.getAnnotation(Position.class)).labelName().equals(""))
          stringBuilder.append(field.getName() + ": ");
        else
          stringBuilder.append(((Position)field.getAnnotation(Position.class)).labelName() + ": ");
        if (field.getType().equals(Collection.class)) {
          mountSubTexts(((Position)field.getAnnotation(Position.class)).index());
        }
        else {
          stringBuilder.append(field.get(storageSettings));
          stringBuilder.append("\n");
        }
      }
  }

  private void mountSubTexts(int index)
  {
    switch (index) {
    case 5:
      fillComparisionmount(this.storageSettings, this.stringBuilder);
      break;
    case 7:
      fillEvaluationMetris(this.storageSettings, this.stringBuilder);
    case 6:
    }
  }

  public void fillComparisionmount(StorageSettings st, StringBuilder sb)
  {
    if (st.comparisions.size() > 1) {
      sb.append('\n');
      sb.append('(');

      for (Comparision comparision : st.comparisions) {
        sb.append('\n');
        sb.append("\talgoritm: " + comparision.getAlgorithtm());
        sb.append("\n");
        sb.append("\tsimilarity: " + comparision.getSimilarity());
        sb.append("\n");
      }
      sb.append(')');
      sb.append('\n');
    }
  }

  public void fillEvaluationMetris(StorageSettings st, StringBuilder sb)
  {
    if (st.evaluationMetrics.size() > 0) {
      sb.append('\n');
      sb.append('(');
      sb.append('\n');
      for (EvaluationMetric evaluationMetric : st.evaluationMetrics) {
        sb.append("\t" + evaluationMetric.getMetric());
        sb.append("\n");
      }
      sb.append(')');
    }
  }
}