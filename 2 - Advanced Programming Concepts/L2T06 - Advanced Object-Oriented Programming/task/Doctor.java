public class Doctor extends Person {
  private int experience;
  private String ranking;

  public Doctor(int height, String hairColor, boolean isAdult, int experience) {
      super(height, hairColor, isAdult);
      this.experience = experience;
      this.ranking = determineRanking(experience);
  }

  // Method to determine the ranking of the doctor based on years of experience
  private String determineRanking(int years) {
      if (years >= 21) return "Attending";
      if (years >= 17) return "Fellow";
      if (years >= 9) return "Resident";
      if (years >= 7) return "Intern";
      return "Medical Student";
  }

  // Getters
  public int getExperience() {
      return experience;
  }

  public String getRanking() {
      return ranking;
  }

  // Setters
  public void setExperience(int experience) {
      this.experience = experience;
  }

  @Override
  public String toString() {
      return "Doctor Details:\n" +
             super.toString() + "\n" +
             "Years of Experience: " + experience + "\n" +
             "Ranking: " + ranking;
  }
}