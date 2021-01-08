import React from 'react';
import {Link} from "react-router-dom";

const manufacturerTerm = (props) => {
    return(
        <tr>
            <td>{props.term.name}</td>
            <td>{props.term.address}</td>
            <td>
                <a title={"Delete"} className={"btn btn-danger"}
                   onClick={() => props.onDelete(props.term.id)}>Delete</a>
                <Link title={"Edit"} className={"btn btn-info ml-2"}
                      onClick={()=>props.onEdit(props.term.id)}
                      to={`/manufacturers/edit/${props.term.id}`}>Edit</Link>
            </td>
        </tr>
    );
}

export default manufacturerTerm;