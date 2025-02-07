
public abstract class FrameDecorator implements Frame {

  // Get the frame object to be decorated
  protected Frame decoratedFrame;

  // Constructor: Assign wrappee to the decoratedFrame
  public FrameDecorator(Frame wrappee) {
    this.decoratedFrame = wrappee;
  }

  // Render the frame
  @Override
  public void render() {
    decoratedFrame.render();
  }
}
