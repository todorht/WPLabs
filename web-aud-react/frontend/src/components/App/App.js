import './App.css';
import React, {Component} from "react";
import {BrowserRouter as Router, Redirect, Route} from "react-router-dom";
import Manufacturers from "../Manufacutrers/ManufacturerList/manufacturers";
import Products from "../Products/ProductList/products";
import Categories from "../Categories/categories";
import Header from "../Header/header";
import ProductAdd from "../Products/ProductAdd/productAdd"
import EShopService from "../../repository/eshopRepository";
import ProductEdit from "../Products/ProductEdit/productEdit";
import ManufacturerAdd from "../Manufacutrers/ManufacturerAdd/manufactorerAdd"
import ManufacturerEdit from "../Manufacutrers/ManufacturerEdit/manufacturerEdit";

class App extends Component{

    constructor(props) {
        super(props);
        this.state={
            manufacturers: [],
            categories: [],
            products: [],
            selectedProduct: [],
            selectedManufacturer: []
        }
    }

    render() {
        return(
           <Router>
                    <Header/>
               <main>
                   <div className="container">
                       <Route path={"/manufacturers/edit/:id"} exact render={() =>
                       <ManufacturerEdit
                                    onEditManufacturer={this.editManufacturer}
                                    manufacturer={this.state.selectedManufacturer}/>}/>
                       <Route path={"/manufacturers/add"} exact render={() =>
                                <ManufacturerAdd onAddManufacturer={this.addManufacturer}/>}/>
                       <Route path={"/manufacturers"} exact render={ () =>
                           <Manufacturers manufacturers ={this.state.manufacturers}
                                          onDelete={this.deleteManufacturer}
                                          onEdit={this.getManufacturer}/>}/>

                       <Route path={"/products/add"} exact render={() =>
                           <ProductAdd categories={this.state.categories}
                                       manufacturers={this.state.manufacturers}
                                       onAddProduct={this.addProduct}/>}/>
                       <Route path={"/products/edit/:id"} exact render={() =>
                           <ProductEdit categories={this.state.categories}
                                        manufacturers={this.state.manufacturers}
                                        onEditProduct={this.editProduct}
                                        product={this.state.selectedProduct}/>}/>
                       <Route path={"/products"} exact render={ () =>
                           <Products products ={this.state.products}
                                     onDelete={this.deleteProduct}
                                     onEdit={this.getProduct}/> }/>
                       <Route path={"/categories"} exact render={ () =>
                           <Categories categories={this.state.categories}/> }/>
                       <Redirect to={"/products"}/>
                   </div>
               </main>
           </Router>
        )
    }

    loadManufacturer = () =>{
        EShopService.fetchManufacturers()
            .then((data)=>{
                this.setState({
                    manufacturers: data.data
                })
            })
    }

    loadProducts = () =>{
        EShopService.fetchProducts()
            .then((data)=>{
                this.setState({
                    products: data.data
                })
            })
    }

    loadCategories = () =>{
        EShopService.fetchCategories()
            .then((data)=>{
                this.setState({
                    categories: data.data
                })
            })
    }

    componentDidMount() {
        this.loadManufacturer();
        this.loadProducts();
        this.loadCategories();
    }

    deleteProduct = (id) =>{
        EShopService.deleteProduct(id)
            .then(()=>this.loadProducts())
    }

    addProduct = (name, price, quantity, category, manufacturer) =>{
        EShopService.addProduct(name, price, quantity, category, manufacturer)
            .then(() => this.loadProducts())
    }

    getProduct = (id) => {
        EShopService.getProduct(id)
            .then((data) => {
                this.setState({
                    selectedProduct: data.data
                })
            })
    }

    editProduct = (id, name, price, quantity, category, manufacturer)=>{
        EShopService.editProduct(id, name, price, quantity, category, manufacturer)
            .then(()=>this.loadProducts())
    }

    deleteManufacturer = (id) =>{
        EShopService.deleteManufacturer(id)
            .then(()=>this.loadManufacturer())
    }

    addManufacturer = (name, address) =>{
        EShopService.addManufacturer(name,address)
            .then(() => this.loadManufacturer())
    }
    getManufacturer = (id) => {
        EShopService.getManufacturer(id)
            .then((data) => {
                this.setState({
                    selectedManufacturer: data.data
                })
            })
    }
    editManufacturer = (id,name,address) =>{
        EShopService.editManufacturer(id,name,address)
            .then(()=>this.loadManufacturer())
    }

}

export default App;
