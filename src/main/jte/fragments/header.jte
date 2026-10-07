@import java.util.Map
@import java.util.Set
@import com.bharat.runbook_automation.auth.AuthContext;

@param Boolean isPatient = false
@param Boolean isAdmin = false
@param Boolean isDoctor = false
@param Boolean isLoggedIn = false
@param Set<String> roles = null
@param AuthContext auth = null

<nav class="navbar navbar-expand-sm bg-dark border-bottom border-body fixed-top" data-bs-theme="dark">
    <div class="container-fluid">

        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#collapsibleNavbar">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="collapsibleNavbar">
            <ul class="navbar-nav">

                <li class="nav-item">
                    <a class="nav-link" href="/">My Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/appointments">Appointments</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/calendar">Calendar</a>
                </li>

                <li class="nav-item">
                    <a class="nav-link" href="/search">Search</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/users/register">Register</a>
                </li>
            </ul>
            <span class="navbar-text float-right d-flex ms-auto">
                @if(roles != null)
                    ${roles.toString()}
                @endif
            </span>
            
            @if(auth != null && auth.loggedIn())
            <form action="/logout" method="post" style="display:inline;" class="d-flex ms-auto me-2">
                <!-- CSRF is currently disabled for simplicity
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                    -->
                <button type="submit" class="btn btn-secondary">${auth != null &&  auth.loggedIn() ? "Logout": "Login"}</button>
            </form>
            @else
            <a href="/users/login" class="btn btn-secondary ms-auto me-2">Login</a>
            @endif
        </div>
    </div>
</nav>