import { createWebHistory, createRouter } from 'vue-router';

const routes =  [
    {
        path: "/",
        name: "SignUpMember",
        component: () => import("./components/SignUpMember"),
    },
    {
        path: "/members",
        name: "ViewAllMembers",
        component: () => import("./components/ViewAllMembers"),
    },
    {
        path: "/members/:memberNo",
        name: "UpdateMember",
        component: () => import("./components/UpdateMember"),
    },

    {
        path: "/members/add",
        name: "AddMember",
        component: () => import("./components/AddMember"),
    },

    {
        path: "/members/login",
        name: "LogIn",
        component: () => import("./components/LogIn"),
    },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;