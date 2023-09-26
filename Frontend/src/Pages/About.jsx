import AboutList from "../About Components/AboutList";
import logo from "../App Components/anglerfish.png";

const About = () => {
  const STUDENT_DATA = [
    {
      id: "s1",
      name: "Brian Mgbokwere",
      image: "../src/About Components/Student Images/Brian.jpg",
      biography:
        "I am a graduate from Northern Illinois University, where I earned my Bachelor of Science in Health Sciences. I have always had an interest in software development.",
    },
    {
      id: "s2",
      name: "Noah Marsteller",
      image: "../src/About Components/Student Images/noah.jpg",
      biography:
        "I am a Cincinnati native, and I work at Amazon Air in Wilmington",
    },
    {
      id: "s3",
      name: "Steven Lin",
      image: "../src/About Components/Student Images/Steven.jpg",
      biography:
        "I recently graduated from Pennsylvania State University with Information Science and Technology degree. IST is a very awesome major, we have cybersecurity class, UI/UX design class and a lots of programming class. This is how I start step into the Tech field",
    },
    {
      id: "s4",
      name: "Yailin Acosta",
      image: "../src/About Components/Student Images/Yailin.jpg",
      biography:
        "I have multiple certifications, in the health and food industry, but I have always had on interested in the tech field. Once I started working at Amazon, I decided to study software development.",
    },
  ];

  return (
    <section>
      <div className='bg-gradient-to-b from-light-blue-300 to-deep-blue-900 min-h-screen p-8'>
        <h1 className="text-2xl font-bold text-center">Meet The Team</h1>
        <ul>
          <AboutList abouts={STUDENT_DATA} />
        </ul>
        <img src={logo} className="w-20 h-20 bottom-0 right-20 static" alt="cute fish" />
      </div>
    </section>
  );
};

export default About;
