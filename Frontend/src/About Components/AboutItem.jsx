import style from "../About Components/aboutitem.module.css";
import PropTypes from "prop-types";

const AboutItem = (props) => {
  return (
    <li className={`${style.item} ${style[props.positionClass]}`}>
      <div className={style.image}>
        <img src={props.image} alt={props.name} />
      </div>
      <div className={style.content}>
        <h3 className={style["name-line"]}>{props.name}</h3>
        <p>{props.biography}</p>
      </div>
    </li>
  );
};

AboutItem.propTypes = {
  image: PropTypes.string,
  name: PropTypes.string,
  biography: PropTypes.string,
};

export default AboutItem;
