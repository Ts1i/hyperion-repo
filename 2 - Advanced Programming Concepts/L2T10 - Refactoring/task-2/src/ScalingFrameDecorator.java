
public class ScalingFrameDecorator extends FrameDecorator {

  private double scaleFactor;

  // Adds scaling functionality to the frame.
  public ScalingFrameDecorator(Frame wrappee, double scaleFactor) {
    // Calls the original render method.
    super(wrappee);
    this.scaleFactor = scaleFactor;
  }

  // Renders the frame with scaling.
  @Override
  public void render() {
    super.render();
    System.out.printf("Imagine making the frame %.1f times bigger!%n", scaleFactor);
  }

  public void scale(double factor) {
    this.scaleFactor = factor;
  }
}
