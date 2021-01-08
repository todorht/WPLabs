import React from "react";
import ManufacturerTerm from '../ManufacturerTerm/manufacturerTerm'
import {Link} from "react-router-dom";

const manufacturers = (props) => {
    return(
        <div className={"container mm-4 mt-5"}>
            <div className={"row"}>
                <div className={"table-responsive"}>
                    <table className={"table table-striped"}>

                        <thead>
                          <tr>
                              <th scope={"col"}>Name</th>
                              <th scope={"col"}>Address</th>
                          </tr>
                         </thead>
                         <tbody>
                            {props.manufacturers.map((term)=>{
                                return(
                                  <ManufacturerTerm term={term}
                                                    onDelete={props.onDelete}
                                                    onEdit={props.onEdit}/>
                                );
                      })}
                      </tbody>
                  </table>
                    <div>
                        <Link className={"btn btn-block btn-dark"} to={"/manufacturers/add"}>Add new manufacturer</Link>
                    </div>
              </div>
          </div>
      </div>
    );
}

export default manufacturers;