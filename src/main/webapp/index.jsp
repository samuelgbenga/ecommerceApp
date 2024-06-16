<%@ page import="java.util.*" %>
<%@ page import="main.ecommerce.app.dao.*" %>
<%@ page import="main.ecommerce.app.model.*" %>
<%@ page import="java.sql.SQLException" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home Page</title>

    <!-- commented the initialize style here to prevent clashing

    <link rel="stylesheet" href="css/index.css">

    -->
    <!---externals links/library begin--->
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <meta content="" name="keywords">
    <meta content="" name="description">

    <!-- Google Web Fonts -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@400;600&family=Raleway:wght@600;800&display=swap" rel="stylesheet">

    <!-- Icon Font Stylesheet -->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">

    <!-- Libraries Stylesheet -->
    <link href="stylingLib/lib/lightbox/css/lightbox.min.css" rel="stylesheet">
    <link href="stylingLib/lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">


    <!-- Customized Bootstrap Stylesheet -->
    <link href="stylingLib/css/bootstrap.min.css" rel="stylesheet">

    <!-- Template Stylesheet -->
    <link href="stylingLib/css/style.css" rel="stylesheet">
    <!---externals links/library End--->

</head>
<body>
<%
    CategoryDAO categoryDAO = new CategoryDAO();
    ProductDAO productDAO = new ProductDAO();
    UserDAO userDAO = new UserDAO();
    CartDAO cartDAO = new CartDAO();
    LikeDAO likeDAO = new LikeDAO();
    CartItemDao cartItemDao = new CartItemDao();

    Map<Category, List<Product>> catMap = new HashMap<>();
    // get category
    List<Category> cat = categoryDAO.getAllCategories();

    // loops through the individual category
    // and adds there belonging product using mapping pair
    cat.forEach(c->{
        List<Product> prodList = productDAO.getProductsByCategory(c.getId());
        catMap.put(c,prodList);
    });


    // get likes by current user




    boolean isCart;
    isCart = Boolean.parseBoolean(request.getParameter("showCart"));


    //String userName = request.getParameter("username");
    String userId = request.getParameter("user_id");
    User user = null;
    String userName = null;
    if(userId != null){
        user = userDAO.getUserById(Integer.parseInt(userId));
        userName = user.getUsername();
    }



    List<Like> currentUserLikes = null;
    int numOfLikes = 0;
    if(userId != null){
         currentUserLikes = likeDAO.getLikesByUserId(Integer.parseInt(userId));
         numOfLikes = currentUserLikes.size();
    }

    // create new product list of current user likes
   List<Integer> userLikedProduct = new ArrayList<>();
    if( currentUserLikes != null && !currentUserLikes.isEmpty()){
        for(int i = 0; i < Objects.requireNonNull(currentUserLikes).size(); i++){
            userLikedProduct.add(currentUserLikes.get(i).getProduct().getId());
        }
    }

   // System.out.println(userLikedProduct);

    // get cart by current user id
    List<Cart> userCartList = new ArrayList<>();
    Cart userCart = null;
    int cartId = 0;
    if(userId != null){

            try{
                userCartList = cartDAO.getCartsByUserId(Integer.parseInt(userId));
                if(userCartList != null && !userCartList.isEmpty()){
                    userCart = userCartList.get(0);
                    cartId = userCart.getId();
                }
            }catch (Exception e){
                System.out.println("something went wrong "+ e.getMessage());
            }
    }

    String setActive = "bg-white";
    String setCountDisplay = "none";
    String setIconDisplay = "";
    assert userCartList != null;
    if(!userCartList.isEmpty()){
        setActive = "bg-primary";
        setCountDisplay = "inline";
        setIconDisplay = "none";
    }

    // get current quantity of current user cart

    int quantity = 0;
    if(userCart != null){
        try {
            quantity = cartItemDao.getCartItemsByCartId(userCart.getId()).size();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


%>
<!-- Navbar start -->
<div class="container-fluid fixed-top">
    <div class="container px-0">
        <nav class="navbar navbar-light bg-white navbar-expand-xl">
            <a href="index.jsp" class="navbar-brand"><h1 class="text-primary display-6">e-Commerce</h1></a>
            <button class="navbar-toggler py-2 px-3" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse">
                <span class="fa fa-bars text-primary"></span>
            </button>
            <div class="collapse navbar-collapse bg-white" id="navbarCollapse">
                <div class="navbar-nav mx-auto">
                    <!-- something can be here i removed the navbar-->
                </div>
                <div class="d-flex m-3 me-0">
                    <%if(!isCart){%>
                    <button class="btn border border-secondary bg-white me-4" onclick="redirectToServlet('new')" >Sign up</button>
                    <button class="btn border border-secondary  bg-white me-4" onclick="redirectToServlet('login')" >Login</button>
                    <%} else {%>
                    <button id="cart" class="btn border border-secondary  <%=setActive%> me-4" onclick="creatCart()">Cart
                        <i class="fa fa-shopping-bag me-2 text-primary" style="display: <%=setIconDisplay%>"></i>
                        <span class="btn  rounded-circle  bg-secondary" style="display: <%=setCountDisplay%>"><%=quantity%></span>
                    </button>
                    <button class="btn border border-secondary  bg-white me-4" >Wish List <span class="btn  rounded-circle  bg-secondary"><%=numOfLikes%></span> </button>
                    <span class="btn   bg-white me-4"><%=userName%></span>
                    <button class="btn border border-secondary  bg-white me-4" onclick="redirectToServlet('logout')" >Log out</button>
                    <%}%>
                </div>
            </div>
        </nav>
    </div>
</div>
<!-- Navbar Ends -->

<!-- Hero Start -->
<div class="container-fluid py-5 mb-5 hero-header">
    <div class="container py-5">
        <div class="row g-5 align-items-center">
            <div class="col-md-12 col-lg-7">
                <h4 class="mb-3 text-secondary">100% Quality Produce</h4>
                <h1 class="mb-5 display-3 text-primary">Quality Goods and Services</h1>
            </div>
            <div class="col-md-12 col-lg-5">
                <div id="carouselId" class="carousel slide position-relative" data-bs-ride="carousel">
                    <div class="carousel-inner" role="listbox">
                        <div class="carousel-item active rounded">
                            <img src="stylingLib/img/hero1.jpg" class="img-fluid w-100 h-100 bg-secondary rounded" alt="First slide">
                            <a href="#" class="btn px-4 py-2 text-white rounded">Camera</a>
                        </div>
                        <div class="carousel-item rounded">
                            <img src="stylingLib/img/hero2.jpg" class="img-fluid w-100 h-100 rounded" alt="Second slide">
                            <a href="#" class="btn px-4 py-2 text-white rounded">Cloths</a>
                        </div>
                    </div>
                    <button class="carousel-control-prev" type="button" data-bs-target="#carouselId" data-bs-slide="prev">
                        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                        <span class="visually-hidden">Previous</span>
                    </button>
                    <button class="carousel-control-next" type="button" data-bs-target="#carouselId" data-bs-slide="next">
                        <span class="carousel-control-next-icon" aria-hidden="true"></span>
                        <span class="visually-hidden">Next</span>
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Hero End -->

<!-- categories Shop Start-->
<%for (Map.Entry<Category, List<Product>> entry : catMap.entrySet()){%>
<div class="container-fluid vesitable py-3">
    <div class="container ">
        <h1 class="mb-0"><%=entry.getKey().getName()%></h1>
        <div class="owl-carousel vegetable-carousel justify-content-center">
            <%

                for(Product prod: entry.getValue()){
                    String setLike = "";
                    String deleteAdd = "insert";

                    if(userLikedProduct.contains(prod.getId())){
                        setLike = "text-primary";
                        deleteAdd = "delete";
                    }
            %>
            <div class="border border-primary rounded position-relative vesitable-item">
                <div class="vesitable-img">
                    <img src="<%=prod.getPictureUrl()%>" class="img-fluid w-100 rounded-top" alt="image of <%=prod.getName()%>">
                </div>

                <div class="p-4 rounded-bottom">
                    <h4><%=prod.getName()%> <i class="btn fa fa-heart me-2 icon <%=setLike%> <%=prod.getId()%>" onclick="redirectAndToggle('<%=prod.getId()%>', '<%=userName%>', '<%=deleteAdd%>')"></i></h4>
                    <p><%=prod.getDescription()%></p>
                    <div class="d-flex justify-content-between flex-lg-wrap">
                        <p class="text-dark fs-5 fw-bold mb-0">$<%=prod.getPrice()%></p>
                        <a onclick="addToCart('<%=prod.getId()%>')" class="btn border border-secondary rounded-pill px-3 text-primary"><i class="fa fa-shopping-bag me-2 text-primary"></i> Add to cart</a>
                    </div>
                </div>
            </div>
            <%}
            %>
        </div>
    </div>
</div>
<%}
%>
<!-- Categories Shop End -->

<!-- Footer Start -->
<div class="container-fluid bg-dark text-white-50 footer pt-5 mt-5">
    <div class="container py-5">
        <div class="pb-4 mb-4" style="border-bottom: 1px solid rgba(226, 175, 24, 0.5) ;">
            <div class="row g-4">
                <div class="col-lg-3">
                    <a href="#">
                        <h1 class="text-primary mb-0">e-Commerce</h1>
                        <p class="text-secondary mb-0">Good products</p>
                    </a>
                </div>
            </div>
        </div>

    </div>
</div>
<!-- Footer End -->

<!-- Back to Top -->
<a href="#" class="btn btn-primary border-3 border-primary rounded-circle back-to-top"><i class="fa fa-arrow-up"></i></a>


</body>

<script>
    // redirect to usersServlet
    function redirectToServlet(action) {
        window.location.href = 'users?action=' + action;
    }


    // redirect to product servlet and toggle like
    let isLike = false;

    function redirectAndToggle(prodId, userName, deleteAdd){
        if(userName!== "null"){
                window.location.href = 'likes?action='+deleteAdd+'&showCart=true&product_id='
                    +prodId+'&user_id=<%=userId%>';
        }
    }

    // handle creat new Cart
    function creatCart(){
        if( 1 > <%=userCartList.size()%> ){
            window.location.href = 'carts?action=insert&status=ACTIVE&showCart=true&user_id=<%=userId%>';
        }
        //
    }

    // add cartItem to cart
    function addToCart(prodId){
        if( 0 < <%=userCartList.size()%> ){
            window.location.href = 'cartItem?action=insert&showCart=true&user_id=<%=userId%>&cart_id=<%=cartId%>&product_id='+prodId
            +'&quantity=<%=++quantity%>';
        }
    }
</script>

<!-- the xml solution to my like problem --->
<script>
    // Ensure `isLike` is defined outside if it's used globally
    /* let isLike = false;

    function handleLike(prodId, userName) {
        if (userName !== "null") {
            const likeIconId = document.getElementsByClassName(prodId);
            for (let i = 0; i < likeIconId.length; i++) {
                likeIconId[i].classList.toggle("text-primary");
            }

            isLike = !isLike;

            // Create an XMLHttpRequest object
            const xhr = new XMLHttpRequest();
            const url = 'likes';
            const params = (isLike) ?'action=insert&product_id='+prodId+'&username='+userName+'&user_id=<%=userId%>' :
                'action=delete&product_id='+prodId+'&username='+userName+'&user_id=<%=userId%>';

            // Configure it: POST-request for the URL /likes
            xhr.open('POST', url, true);

            // Set the request header
            xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');

            // Handle the response
            xhr.onreadystatechange = function() {
                if (xhr.readyState === 4 && xhr.status === 200) {
                    // Parse the response JSON
                    const response = JSON.parse(xhr.responseText);
                    console.log(response.message);
                } else if (xhr.readyState === 4) {
                    console.error('Error'+ xhr.status+'}:'+xhr.statusText+'}');
                }
            };

            // Send the request over the network
            xhr.send(params);
        }
    } */
</script>

<!-- JavaScript Libraries from the template -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
<script src="stylingLib/lib/easing/easing.min.js"></script>
<script src="stylingLib/lib/waypoints/waypoints.min.js"></script>
<script src="stylingLib/lib/lightbox/js/lightbox.min.js"></script>
<script src="stylingLib/lib/owlcarousel/owl.carousel.min.js"></script>
<!-- Template Javascript -->
<script src="stylingLib/js/main.js"></script>

</html>

