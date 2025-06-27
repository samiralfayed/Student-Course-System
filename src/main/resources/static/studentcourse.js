const API_BASE = 'http://localhost:8081/api';

async function loadStudents() {
    const res = await fetch(`${API_BASE}/students`);
    const students = await res.json();
    const list = document.getElementById("student-list");
    list.innerHTML = '';
    students.forEach(s => {
        const li = document.createElement('li');
        li.className = "py-2 border-b flex justify-between";
        li.innerHTML = `<span><strong>${s.name}</strong> - ${s.email}</span><span>Courses: ${s.courses.map(c => c.title).join(', ')}</span>`;
        list.appendChild(li);
    });
}

async function loadCourses() {
    const res = await fetch(`${API_BASE}/courses`);
    const courses = await res.json();
    const list = document.getElementById("course-list");
    list.innerHTML = '';
    courses.forEach(c => {
        const li = document.createElement('li');
        li.className = "py-2 border-b flex justify-between";
        li.innerHTML = `<span><strong>${c.title}</strong></span><span>${c.description}</span>`;
        list.appendChild(li);
    });
}

async function handleStudentSubmit(event) {
    event.preventDefault();
    const name = document.getElementById('student-name').value;
    const email = document.getElementById('student-email').value;
    const courseIds = document.getElementById('student-courses').value
        .split(',')
        .map(id => parseInt(id.trim()));

    await fetch(`${API_BASE}/students`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ name, email, courseIds })
    });

    loadStudents();
}

function init() {
    document.getElementById('student-form').addEventListener('submit', handleStudentSubmit);
    loadStudents();
    loadCourses();
}

window.onload = init;
