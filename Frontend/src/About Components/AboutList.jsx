import AboutItem from "./AboutItem";
import style from "../About Components/AboutList.module.css";
import PropTypes from "prop-types";

const AboutList = (props) => {
  const positionClasses = ["", "second", "third", "fourth", "fifth"];
  return (
    <ul className={style.list}>
      {props.abouts.map((about, index) => (
        <AboutItem
          key={about.id}
          name={about.name}
          image={about.image}
          biography={about.biography}
          positionClass={positionClasses[index]}
        />
      ))}
    </ul>
  );
};

AboutList.propTypes = {
  abouts: PropTypes.string,
};

export default AboutList;
