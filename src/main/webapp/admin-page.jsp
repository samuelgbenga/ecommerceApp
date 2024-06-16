<%--
  Created by IntelliJ IDEA.
  User: apple
  Date: 12/06/2024
  Time: 1:15 am
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="main.ecommerce.app.model.Category" %>
<%@ page import="main.ecommerce.app.model.Product" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>

<%Map<Category,List<Product>> catMap = (Map<Category, List<Product>>) request.getAttribute("catMap");
    String userName = (String) request.getAttribute("userName");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard</title>
    <link rel="stylesheet" href="css/admin-page1.css">
</head>
<body>
<div class="container">
    <div class="sidebar">
        <h2><%=userName%></h2>
        <h4>Creat New Category</h4>
        <div class="form-container-cat">

            <!--- this would be the category form begin----->
            <form id="cat-form" action="users?post-action=addNewCat&username=<%=userName%>" method="post" >
                <div class="form-group-cat">
                    <label for="cat-name">Name:</label>
                    <input type="text" id="cat-name" name="name" maxlength="50" required>
                </div>
                <div class="form-group-cat">
                    <label for="cat-description">Description:</label>
                    <textarea id="cat-description" name="description"></textarea>
                </div>
                <button type="submit" style="width: 100%; cursor: pointer">Submit</button>
            </form>
            <!--- this would be the category form ends----->

            <!--- this would be the invisible update form begin----->
            <form id='update-cat-form' style="display: none" action="users?post-action=updateCat&username=<%=userName%>" method="post" >
                <div class="form-group-cat">
                    <label for="update-cat-name">Name:</label>
                    <input type="text" id="update-cat-name" name="name" maxlength="50" required>
                </div>
                <div class="form-group-cat">
                    <label for="update-cat-description">Description:</label>
                    <textarea id="update-cat-description" name="description"></textarea>
                </div>
                <input type="hidden" name="update-category_id" id="update-category-id">
                <button type="submit" style="width: 100%; cursor: pointer">Update</button>
            </form>

            <!--- this would be the invisible update form ending----->
        </div>
        <ul><li>
            <button onclick="redirectToServlet('logout')">Logout</button>
        </li></ul>
        <!--update a particular product begin from here-->
        <div id="update-row-hide" class="form-container" style="display: none">
            <form action="users?post-action=updateProd&username=<%=userName%>" method="post">
                <div class="form-group">
                    <label for="prod-name">Name</label>
                    <input type="text" id="prod-name" name="name" maxlength="100" required>
                </div>

                <!-- update inserts the picture Begin--->
                <div class="form-group">
                    <label for="update-picture-url">Picture Url</label>
                    <input type="text" id="update-picture-url" name="update-picture-url" maxlength="100" required>
                </div>
                <!-- update inserts the picture Ending--->

                <div class="form-group">
                    <label for="prod-description">Description</label>
                    <textarea id="prod-description" name="description" rows="4"></textarea>
                </div>
                <div class="form-group">
                    <label for="prod-price">Price</label>
                    <input type="number" id="prod-price" name="price" step="0.01" required>
                </div>
                <input type="hidden" name="category_id" id="category-id">
                <input type="hidden" name="product_id" id="product-id">
                <div class="form-group">
                    <button type="submit">Submit</button>
                </div>
            </form>

        </div>

        <!--update a particular product Ending-->

    </div>
    <div class="main-content">
        <%for (Map.Entry<Category, List<Product>> entry : catMap.entrySet()){%>
        <div class="table-category">
            <span class="h2" onclick="toggleCatDesc('<%=entry.getKey()%>')" style="cursor: pointer"><%=entry.getKey().getName()%></span>
            <span class="update" onclick="updateCategory('<%=entry.getKey().getName()%>','<%=entry.getKey().getDescription()%>', '<%=entry.getKey().getId()%>' )" >Update</span>
            <span class="remove" onclick="redirectForCatDelete('deleteCat',<%=entry.getKey().getId()%>, '<%=userName%>')" >Remove</span>
            <p class="cat-p" style="display: none" id='<%=entry.getKey()%>'><%=entry.getKey().getDescription()%></p>
            <table>
                <tr>
                    <th class="num">No</th>
                    <th class="prod-name">Product Name</th>
                    <th>Description</th>
                    <th>Picture Url</th>
                    <th class="price">Price</th>
                    <th class="edit">Edit</th>
                </tr>
                <% int counter = 1;
                    for(Product prod: entry.getValue()){
                 %>
                <tr>
                    <td class="num"><%=counter++%></td>
                    <td class="prod-name"><%=prod.getName()%></td>
                    <td><%=prod.getDescription()%></td>
                    <td><%=prod.getPictureUrl()%></td>
                    <td class="price"><%=prod.getPrice()%></td>
                    <td class="edit">
                        <div class="parent-div">
                            <button class="btn update-btn" id="toggleFormBtn" onclick="toggleUpdateRow('<%=prod.getName()%>','<%=prod.getDescription()%>', <%=prod.getPrice()%>, <%=prod.getCategoryId()%>, <%=prod.getId()%>, '<%=prod.getPictureUrl()%>' )">Update</button>
                            <button class="btn delete-btn" onclick="redirectProductServlet('adminDelete',<%=prod.getId()%>, '<%=userName%>')" >Delete</button>
                        </div>
                    </td>
                </tr>
                <!--This here should settle the argument of adding to cart-->
                <!--This here should settle the argument of adding to cart-->
                <%
                    }
                %>
            </table>
            <!--Begin of category form---->
            <div><button class="btn add-btn" onclick="toggleShowAdd('<%=entry.getKey().getId()%>')">Add</button></div>

            <!-- Begin of product form--->
            <div id="<%=entry.getKey().getId()%>" class="form-container">
                <form action="users?post-action=adminPage&username=<%=userName%>" method="post">
                    <div class="form-group">
                        <label for="name">Name</label>
                        <input type="text" id="name" name="name" maxlength="100" required>
                    </div>
                    <!-- the picture input i included begin-->
                    <div class="form-group">
                        <label for="picture-url">Picture Url</label>
                        <input type="text" id="picture-url" name="picture-url" maxlength="100" required>
                    </div>
                    <!-- the picture input i included end-->

                    <div class="form-group">
                        <label for="description">Description</label>
                        <textarea id="description" name="description" rows="4"></textarea>
                    </div>
                    <div class="form-group">
                        <label for="price">Price</label>
                        <input type="number" id="price" name="price" step="0.01" required>
                    </div>
                    <input type="hidden" name="category_id" value=<%=entry.getKey().getId()%>>
                    <div class="form-group">
                        <button type="submit">Submit</button>
                    </div>
                </form>
            </div>
            <!-- End of product form--->

        </div>
        <!-- Add more table categories as needed -->
        <%}
        %>
    </div>
</div>

<!-- The absolut table for the update the product--->

</body>

<script>
    // JavaScript variable to toggle the visibility
    let showAdd = false;

    function toggleShowAdd(id) {
        showAdd = !showAdd;
        document.getElementById(id).style.display = showAdd ? 'block' : 'none';
    }

    let showCatDesc = false;

    function toggleCatDesc(id) {
        showCatDesc = !showCatDesc;
        document.getElementById(id).style.display = showCatDesc ? 'block' : 'none';
    }

    // this is to toggle update invisible update form
    let updateRow = false;

    function toggleUpdateRow(prodName, prodDescription, prodPrice, prodCat, prodId, prodUrl) {
        updateRow = !updateRow;
        document.getElementById('update-row-hide').style.display = updateRow ? 'block' : 'none';

        if(updateRow){
            const productName = document.getElementById('prod-name');
            const productDesc = document.getElementById('prod-description');
            const productPrice = document.getElementById('prod-price');
            const productCat = document.getElementById('category-id');
            const productId = document.getElementById('product-id');
            const productUrl = document.getElementById('update-picture-url')

            productName.value = prodName;
            productDesc.value = prodDescription;
            productPrice.value = prodPrice;
            productCat.value = prodCat;
            productId.value = prodId;
            productUrl.value = prodUrl;
        }
    }

    // handle updating category
    let updateCat = false;
    function updateCategory(catName, catDescription, catId) {
        updateCat = !updateCat;
        document.getElementById('update-cat-form').style.display = updateCat ? 'block': 'none';

        if(updateCat){
            document.getElementById('cat-form').style.display = 'none';
            const categoryName = document.getElementById('update-cat-name');
            const categoryDescription = document.getElementById('update-cat-description');
            const categoryId = document.getElementById('update-category-id')
            categoryName.value = catName;
            categoryDescription.value = catDescription;
            categoryId.value = catId;
        }else {
            document.getElementById('cat-form').style.display = 'block';
        }

    }

    // redirect the admin to home page
    function redirectToServlet(action) {
        window.location.href = 'users?action=' + action;
    }

    function redirectProductServlet(action, prod_id, userName) {
        window.location.href = 'products?action=' + action+'&id='+prod_id+'&username='+userName;
    }

    // delete category from the userServlet instead of the product to
    //  reduce the number of back and forth.
    function redirectForCatDelete(action, category_id, userName) {
        window.location.href = 'users?action=' + action+'&id='+category_id+'&username='+userName;
    }

</script>
</html>
