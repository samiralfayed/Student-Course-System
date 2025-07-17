function login() {
    const email = document.getElementById('email').value;
    const role = document.getElementById('role').value;
    if (email && role) {
        localStorage.setItem('userEmail', email);
        localStorage.setItem('userRole', role);
        loadDashboard();
    }
}

function logout() {
    localStorage.clear();
    location.reload();
}

function loadDashboard() {
    const role = localStorage.getItem('userRole');
    if (role) {
        document.getElementById('login-section').style.display = 'none';
        document.getElementById('dashboard-section').style.display = 'block';
        document.getElementById('userRole').innerText = role;

        if (role === 'STUDENT') {
            document.getElementById('student-dashboard').style.display = 'block';
        } else if (role === 'ADMIN') {
            document.getElementById('admin-dashboard').style.display = 'block';
            fetchStudents();
            fetchCourses();
        }
    }
}

function showToast(message, type = "success") {
    const toastContainer = document.getElementById("toastContainer");
    const toast = document.createElement("div");
    toast.className = `toast align-items-center text-bg-${type} border-0 show`;
    toast.role = "alert";
    toast.innerHTML = `
    <div class="d-flex">
      <div class="toast-body">${message}</div>
      <button type="button" class="btn-close btn-close-white me-2 m-auto" onclick="this.parentElement.parentElement.remove()"></button>
    </div>
  `;
    toastContainer.appendChild(toast);
    setTimeout(() => toast.remove(), 4000);
}

// Dummy data functions (Replace with real fetch calls)
function fetchStudents() {
    const list = document.getElementById("studentsList");
    list.innerHTML = `<ul><li>John Doe <button onclick="editStudent()">Edit</button> <button onclick="deleteStudent()">Delete</button></li></ul>`;
}
function fetchCourses() {
    const list = document.getElementById("coursesList");
    list.innerHTML = `<ul><li>Math 101 <button onclick="editCourse()">Edit</button> <button onclick="deleteCourse()">Delete</button></li></ul>`;
}
function saveStudent(e) {
    e.preventDefault();
    const name = document.getElementById("studentName").value;
    showToast(`Student "${name}" saved!`);
    bootstrap.Modal.getInstance(document.getElementById("studentModal")).hide();
}
function saveCourse(e) {
    e.preventDefault();
    const name = document.getElementById("courseName").value;
    showToast(`Course "${name}" saved!`);
    bootstrap.Modal.getInstance(document.getElementById("courseModal")).hide();
}
