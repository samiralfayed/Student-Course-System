// js/app.js

document.addEventListener("DOMContentLoaded", () => {
    const sections = {
        students: document.getElementById("students-section"),
        courses: document.getElementById("courses-section"),
    };

    document.querySelectorAll("aside a").forEach((link) => {
        link.addEventListener("click", (e) => {
            e.preventDefault();
            const target = link.getAttribute("href").substring(1);
            Object.keys(sections).forEach((key) => {
                sections[key].classList.toggle("hidden", key !== target);
            });
        });
    });

    const modalOverlay = document.getElementById("modal-overlay");
    const modalForm = document.getElementById("modal-form");
    const modalTitle = document.getElementById("modal-title");

    document.getElementById("modal-cancel").addEventListener("click", () => {
        modalOverlay.classList.add("hidden");
    });

    document.getElementById("add-student-btn").addEventListener("click", () => {
        showModal("Add Student");
    });

    document.getElementById("add-course-btn").addEventListener("click", () => {
        showModal("Add Course");
    });

    function showModal(title) {
        modalTitle.textContent = title;
        modalForm.reset();
        modalOverlay.classList.remove("hidden");
    }

    // Hide modal initially
    modalOverlay.classList.add("hidden");
});