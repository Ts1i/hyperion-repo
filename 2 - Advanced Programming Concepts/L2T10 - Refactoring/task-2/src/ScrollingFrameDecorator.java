
public class ScrollingFrameDecorator extends FrameDecorator {

  private int numScrollBars;

  // Adds scrolling functionality to the frame.
  public ScrollingFrameDecorator(Frame wrappee, int numScrollBars) {
    super(wrappee);
    this.numScrollBars = numScrollBars;
  }

  @Override
  public void render() {
    super.render();
    System.out.printf("Imagine adding %d scroll bars to the frame!%n", numScrollBars);
  }

  // Adds a scroll bar to the frame.
  public void addScrollBar() {
    numScrollBars++;
  }
}
