
public class Main {

  public static void main(String[] args) {

    System.out.println("The basic frame:");
    Frame basicFrame = new BasicFrame();
    basicFrame.render();

    System.out.println("\nThe scaled frame:");
    Frame scaledFrame = new ScalingFrameDecorator(new BasicFrame(), 2.0);
    scaledFrame.render();

    System.out.println("\nThe scrollable frame:");
    Frame scrollableFrame = new ScrollingFrameDecorator(new BasicFrame(), 2);
    scrollableFrame.render();
    
  }
}
