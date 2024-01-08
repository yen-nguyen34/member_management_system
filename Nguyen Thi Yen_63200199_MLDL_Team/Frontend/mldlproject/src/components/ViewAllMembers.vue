<template>
  <div class="relative shadow-md sm:rounded-lg">
    <br />

    <!-- SEARCH FORM -->

    <form class="w-full" @submit.prevent="handleSearchParams">
      <div
        class="mt-2 text-sm font-bold text-red-600 dark:text-red-500"
        v-bind:key="index"
        v-for="(error, index) in errors"
      >
        {{ error }}
      </div>
      <div class="flex flex-wrap -mx-3 mb-6">
        <!-- Search ID -->
        <div class="w-full md:w-1/3 px-3 mb-6 md:mb-0">
          <label
            class="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2"
            for="grid-first-name"
            >ID</label
          >
          <input
            type="text"
            class="block border border-grey-light w-full py-3 px-4 mb-3 rounded"
            name="memberId"
            placeholder="Search ID"
            v-model="searchParams.memberId"
          />
        </div>
        <!-- Search Name -->
        <div class="w-full md:w-1/3 px-3 mb-6 md:mb-0">
          <label
            class="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2"
            for="grid-first-name"
            >Name</label
          >
          <input
            type="text"
            class="block border border-grey-light w-full py-3 px-4 mb-3 rounded"
            name="memberName"
            placeholder="Search Name"
            v-model="searchParams.memberName"
          />
        </div>
        <!-- Search Phone -->
        <div class="w-full md:w-1/3 px-3 mb-6 md:mb-0">
          <label
            class="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2"
            for="grid-first-name"
            >Phone</label
          >
          <input
            type="text"
            class="block border border-grey-light w-full py-3 px-4 mb-3 rounded"
            name="memberPhone"
            placeholder="Search Phone"
            v-model="searchParams.memberPhone"
          />
        </div>
        <!-- Search Date -->
        <div date-rangepicker class="flex items-center" id="fbDateRangePicker">
          <div class="relative">
            <div
              class="absolute inset-y-0 start-0 flex items-center ps-3 pointer-events-none"
            >
              <svg
                class="w-4 h-4 text-gray-500 dark:text-gray-400"
                aria-hidden="true"
                xmlns="http://www.w3.org/2000/svg"
                fill="currentColor"
                viewBox="0 0 20 20"
              >
                <path
                  d="M20 4a2 2 0 0 0-2-2h-2V1a1 1 0 0 0-2 0v1h-3V1a1 1 0 0 0-2 0v1H6V1a1 1 0 0 0-2 0v1H2a2 2 0 0 0-2 2v2h20V4ZM0 18a2 2 0 0 0 2 2h16a2 2 0 0 0 2-2V8H0v10Zm5-8h10a1 1 0 0 1 0 2H5a1 1 0 0 1 0-2Z"
                />
              </svg>
            </div>
            <input
              name="startDate"
              type="text"
              id="startDate"
              class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full ps-10 p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
              placeholder="Select date start"
            />
          </div>
          <span class="mx-4 text-gray-500">to</span>
          <div class="relative">
            <div
              class="absolute inset-y-0 start-0 flex items-center ps-3 pointer-events-none"
            >
              <svg
                class="w-4 h-4 text-gray-500 dark:text-gray-400"
                aria-hidden="true"
                xmlns="http://www.w3.org/2000/svg"
                fill="currentColor"
                viewBox="0 0 20 20"
              >
                <path
                  d="M20 4a2 2 0 0 0-2-2h-2V1a1 1 0 0 0-2 0v1h-3V1a1 1 0 0 0-2 0v1H6V1a1 1 0 0 0-2 0v1H2a2 2 0 0 0-2 2v2h20V4ZM0 18a2 2 0 0 0 2 2h16a2 2 0 0 0 2-2V8H0v10Zm5-8h10a1 1 0 0 1 0 2H5a1 1 0 0 1 0-2Z"
                />
              </svg>
            </div>
            <input
              name="endDate"
              type="text"
              id="endDate"
              class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full ps-10 p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
              placeholder="Select date end"
            />
          </div>
        </div>
      </div>

      <div>
        <button
          type="submit"
          class="focus:outline-none text-white bg-teal-500 hover:bg-teal-500 focus:ring-4 focus:ring-red-300 font-medium rounded-lg text-sm px-5 py-2.5 me-2 mb-2 dark:bg-red-600 dark:hover:bg-red-700 dark:focus:ring-red-900"
        >
          Search
        </button>

        <button
          @click.prevent="getSearchResult"
          type="button"
          class="bg-gray-300 hover:bg-gray-400 text-gray-800 font-bold py-2 px-4 rounded inline-flex items-center"
        >
          <svg
            class="fill-current w-4 h-4 mr-2"
            xmlns="http://www.w3.org/2000/svg"
            viewBox="0 0 20 20"
          >
            <path d="M13 8V2H7v6H2l8 8 8-8h-5zM0 18h20v2H0v-2z" />
          </svg>
          <span>Download</span>
        </button>
      </div>
    </form>
    <div></div>
    <br />

    <!-- Display search results -->
    <div v-if="paginatedMembers.length > 0">
      <table
        class="w-full text-sm text-left rtl:text-center text-gray-500 dark:text-gray-400"
      >
        <thead
          class="text-xs text-gray-700 uppercase bg-gray-300 dark:bg-gray-700 dark:text-gray-400"
        >
          <tr>
            <th scope="col" class="px-6 py-3">No.</th>
            <th scope="col" class="px-6 py-3">ID</th>
            <th scope="col" class="px-6 py-3">Name</th>
            <th scope="col" class="px-6 py-3">Phone</th>
            <th scope="col" class="px-6 py-3">Email</th>
            <th scope="col" class="px-6 py-3">Sign-up Date</th>
            <th scope="col" class="px-6 py-3">Action</th>
          </tr>
        </thead>
        <tbody>
          <tr
            v-for="(member, index) in paginatedMembers"
            :key="index"
            class="bg-white border-b dark:bg-gray-800 dark:border-gray-700 hover:bg-gray-50 dark:hover:bg-gray-600"
          >
            <td class="px-6 py-4">
              {{ member.memberNo }}
            </td>
            <td class="px-6 py-4">
              {{ member.memberId }}
            </td>
            <td class="px-6 py-4">
              {{ member.memberName }}
            </td>
            <td class="px-6 py-4">
              {{ member.memberPhone }}
            </td>
            <td class="px-6 py-4">
              {{ member.memberEmail }}
            </td>
            <td class="px-6 py-4">
              {{ formatSignUpDate(member.signUpDate) }}
            </td>
            <td class="px-6 py-4">

              <!-- UPDATE BUTTON -->
              <button
                type="button"
                class="focus:outline-none px-5"
                @click="showUpdateButton(member.memberNo)"
              >
                <svg
                  class="w-6 h-6 text-teal-500 dark:text-white"
                  aria-hidden="true"
                  xmlns="http://www.w3.org/2000/svg"
                  fill="currentColor"
                  viewBox="0 0 20 18"
                >
                  <path
                    d="M12.687 14.408a3.01 3.01 0 0 1-1.533.821l-3.566.713a3 3 0 0 1-3.53-3.53l.713-3.566a3.01 3.01 0 0 1 .821-1.533L10.905 2H2.167A2.169 2.169 0 0 0 0 4.167v11.666A2.169 2.169 0 0 0 2.167 18h11.666A2.169 2.169 0 0 0 16 15.833V11.1l-3.313 3.308Zm5.53-9.065.546-.546a2.518 2.518 0 0 0 0-3.56 2.576 2.576 0 0 0-3.559 0l-.547.547 3.56 3.56Z"
                  />
                  <path
                    d="M13.243 3.2 7.359 9.081a.5.5 0 0 0-.136.256L6.51 12.9a.5.5 0 0 0 .59.59l3.566-.713a.5.5 0 0 0 .255-.136L16.8 6.757 13.243 3.2Z"
                  />
                </svg>
              </button>

              <!-- UPDATE CONFIRM FORM -->


              <!-- DELETE BUTTON -->
              <button
                type="button"
                class="focus:outline-none px-5"
                @click="showDeleteButton"
              >
                <svg
                  class="w-6 h-6 text-red-500 dark:text-white"
                  aria-hidden="true"
                  xmlns="http://www.w3.org/2000/svg"
                  fill="currentColor"
                  viewBox="0 0 20 18"
                >
                  <path
                    d="M6.5 9a4.5 4.5 0 1 0 0-9 4.5 4.5 0 0 0 0 9ZM8 10H5a5.006 5.006 0 0 0-5 5v2a1 1 0 0 0 1 1h11a1 1 0 0 0 1-1v-2a5.006 5.006 0 0 0-5-5Zm11-3h-6a1 1 0 1 0 0 2h6a1 1 0 1 0 0-2Z"
                  />
                </svg>
              </button>

              <!-- DELETE CONFIRM FORM -->

              <div
                v-if="confirmDeleteForm"
                :class="{ 'blur-background': confirmDeleteForm }"
                class="fixed inset-0 flex items-center justify-center bg-opacity-75 backdrop-blur-md"
              >
                <div
                  class="bg-white dark:bg-gray-800 p-6 border rounded-lg shadow-lg"
                >
                  <div class="flex justify-end">
                    <button
                      @click="closeDeleteConfirmForm"
                      type="button"
                      class="text-gray-400 bg-transparent hover:bg-gray-200 hover:text-gray-900 rounded-lg text-sm p-1.5 ml-auto inline-flex items-center"
                    >
                      <svg
                        class="w-5 h-5"
                        fill="currentColor"
                        viewBox="0 0 20 20"
                        xmlns="http://www.w3.org/2000/svg"
                      >
                        <path
                          fill-rule="evenodd"
                          d="M4.293 4.293a1 1 0 011.414 0L10 8.586l4.293-4.293a1 1 0 111.414 1.414L11.414 10l4.293 4.293a1 1 0 01-1.414 1.414L10 11.414l-4.293 4.293a1 1 0 01-1.414-1.414L8.586 10 4.293 5.707a1 1 0 010-1.414z"
                          clip-rule="evenodd"
                        ></path>
                      </svg>
                    </button>
                  </div>
                  <div class="p-6 pt-0 text-center">
                    <svg
                      class="w-20 h-20 text-red-600 mx-auto"
                      fill="none"
                      stroke="currentColor"
                      viewBox="0 0 24 24"
                      xmlns="http://www.w3.org/2000/svg"
                    >
                      <path
                        stroke-linecap="round"
                        stroke-linejoin="round"
                        stroke-width="2"
                        d="M12 8v4m0 4h.01M21 12a9 9 0 11-18 0 9 9 0 0118 0z"
                      ></path>
                    </svg>
                    <h3 class="text-xl font-normal text-gray-500 mt-5 mb-6">
                      Are you sure you want to delete this member?
                    </h3>
                    <button
                      type="button"
                      @click="confirmDelete(member)"
                      class="text-white bg-red-600 hover:bg-red-800 focus:ring-4 focus:ring-red-300 font-medium rounded-lg text-base inline-flex items-center px-3 py-2.5 text-center mr-2"
                    >
                      Yes, I'm sure
                    </button>
                    <button
                      @click="closeDeleteConfirmForm"
                      type="button"
                      class="text-gray-900 bg-white hover:bg-gray-100 focus:ring-4 focus:ring-cyan-200 border border-gray-200 font-medium inline-flex items-center rounded-lg text-base px-3 py-2.5 text-center"
                    >
                      No, cancel
                    </button>
                  </div>
                </div>
              </div>


            </td>
          </tr>
        </tbody>
      </table>
    </div>

    <div v-else class="mt-2 text-sm font-bold text-red-600 dark:text-red-500">
      No members found!
    </div>

    <!-- display pagination -->
    <div class="mt-4 flex justify-between items-center">
      <!-- Help text -->
      <span class="text-sm text-gray-700 dark:text-gray-400">
        Showing
        <span class="font-semibold text-gray-900 dark:text-white">{{
          currentPage
        }}</span>
        of
        <span class="font-semibold text-gray-900 dark:text-white">{{
          totalPages
        }}</span>
      </span>
      <!-- Buttons -->
      <div class="inline-flex mt-2 xs:mt-0">
        <button
          class="flex items-center justify-center px-3 h-8 text-sm font-medium text-white bg-slate-400 rounded-s hover:bg-gray-900 dark:bg-gray-800 dark:border-gray-700 dark:text-gray-400 dark:hover:bg-gray-700 dark:hover:text-white"
          @click="prevPage"
          :disabled="currentPage === 1"
        >
          <svg
            class="w-3.5 h-3.5 me-2 rtl:rotate-180"
            aria-hidden="true"
            xmlns="http://www.w3.org/2000/svg"
            fill="none"
            viewBox="0 0 14 10"
          >
            <path
              stroke="currentColor"
              stroke-linecap="round"
              stroke-linejoin="round"
              stroke-width="2"
              d="M13 5H1m0 0 4 4M1 5l4-4"
            />
          </svg>
          Prev
        </button>
        <button
          class="flex items-center justify-center px-3 h-8 text-sm font-medium text-white bg-slate-400 border-0 border-s border-gray-700 rounded-e hover:bg-gray-900 dark:bg-gray-800 dark:border-gray-700 dark:text-gray-400 dark:hover:bg-gray-700 dark:hover:text-white"
          @click="nextPage"
          :disabled="currentPage === totalPages"
        >
          Next
          <svg
            class="w-3.5 h-3.5 ms-2 rtl:rotate-180"
            aria-hidden="true"
            xmlns="http://www.w3.org/2000/svg"
            fill="none"
            viewBox="0 0 14 10"
          >
            <path
              stroke="currentColor"
              stroke-linecap="round"
              stroke-linejoin="round"
              stroke-width="2"
              d="M1 5h12m0 0L9 1m4 4L9 9"
            />
          </svg>
        </button>
      </div>
      <div>
        Total Members:
        <span class="font-semibold text-gray-900 dark:text-white">{{
          members.length
        }}</span>
      </div>
    </div>
  </div>
</template>

<script>
import MemberService from "@/service/MemberService";
import DateRangePicker from "flowbite-datepicker/DateRangePicker";

export default {
  name: "ViewAllMembers",
  data() {
    return {
      members: [],
      memberListSearch: [],
      searchParams: {
        memberId: "",
        memberName: "",
        memberPhone: "",
        startDate: "",
        endDate: "",
      },
      errors: [],
      itemsPerPage: 5,
      currentPage: 1,
      confirmDeleteForm: false,
      memberToDelete: null,
    };
  },

  computed: {
    totalPages() {
      return Math.ceil(this.members.length / this.itemsPerPage);
    },

    paginatedMembers() {
      const startIndex = (this.currentPage - 1) * this.itemsPerPage;
      const endIndex = startIndex + this.itemsPerPage;
      return this.members.slice(startIndex, endIndex);
    },
  },

  methods: {
    refresherMembers() {
      MemberService.retrieveAllMembers().then((res) => {
        this.members = res.data;
      });
    },

    formatSignUpDate(date) {
      const dateSplit = date.split("T")[0];
      const dateFormatSplit = dateSplit.split("-");
      return (
        dateFormatSplit[2] + "/" + dateFormatSplit[1] + "/" + dateFormatSplit[0]
      );
    },

    verifySearchParams() {
      let valid = true;
      this.searchParams.startDate = document.getElementById("startDate").value;
      this.searchParams.endDate = document.getElementById("endDate").value;

      if (new Date(this.searchParams.startDate) != "Invalid Date") {
        const splitStart = this.searchParams.startDate.split("/");
        this.searchParams.startDate =
          splitStart[2] +
          "-" +
          splitStart[0] +
          "-" +
          splitStart[1] +
          " 00:00:00.000000";
        const splitEnd = this.searchParams.endDate.split("/");
        this.searchParams.endDate =
          splitEnd[2] +
          "-" +
          splitEnd[0] +
          "-" +
          splitEnd[1] +
          " 23:59:59.999999";
      }

      if (
        this.searchParams.memberId.length > 0 &&
        this.searchParams.memberId.length < 3
      ) {
        valid = false;
        this.errors.push("Please enter ID at least 3 characters!");
      }

      if (
        this.searchParams.memberName.length > 0 &&
        this.searchParams.memberName.length < 2
      ) {
        valid = false;
        this.errors.push("Please enter Name at least 2 characters!");
      }

      if (valid) {
        this.errors = [];
      }
    },

    handleSearchParams() {
      this.verifySearchParams();
      if (this.errors == 0) {
        MemberService.searchMembers(this.searchParams)
          .then((res) => {
            this.members = res.data;
          })
          .catch((error) => {
            console.log("Error fetching search results:", error);
            this.members = [];
          });
      }
    },

    // download search result
    getSearchResult() {
      this.verifySearchParams();
      if (this.errors == 0) {
        MemberService.downloadMembers(this.searchParams)
          .then((response) => {
            const href = URL.createObjectURL(response.data);

            // create "a" HTML element with href to file & click
            const link = document.createElement("a");
            link.href = href;
            link.setAttribute("download", "exported_members.xlsx"); //or any other extension
            document.body.appendChild(link);
            link.click();

            // clean up "a" element & remove ObjectURL
            document.body.removeChild(link);
            URL.revokeObjectURL(href);
          })
          .catch((error) => {
            console.log("Error fetching search results:", error);
          });
      }
    },

    nextPage() {
      if (this.currentPage < this.totalPages) {
        this.currentPage++;
      }
    },

    prevPage() {
      if (this.currentPage > 1) {
        this.currentPage--;
      }
    },

    showUpdateButton(memberNo){
      this.$router.push(`/members/${memberNo}`);
    },

    showDeleteButton() {
      // ẩn confirm form bằng cách set confirmDeleteForm=false,
      // muốn hiển thị khi ấn thì set confirmDeleteForm=true
      this.confirmDeleteForm = !this.confirmDeleteForm;
    },

    closeDeleteConfirmForm() {
      this.confirmDeleteForm = false;
    },

    confirmDelete(member) {
      this.memberToDelete = member;
      if (this.memberToDelete) {
        MemberService.deleteMember(this.memberToDelete.memberNo)
          .then((res) => {
            this.refresherMembers();
            this.closeDeleteConfirmForm();
          })
          .catch((error) => {
            console.log("Error delete member!", error);
          });
      }
    },
  },

  mounted() {
    // Set the base date from one year ago
    const oneYearAgo = new Date();
    oneYearAgo.setFullYear(oneYearAgo.getFullYear() - 1);

    // Format the dates as strings (YYYY-MM-DD)
    const startDate = oneYearAgo.toISOString().split("T")[0];
    const endDate = new Date().toISOString().split("T")[0];

    // Set the values in your searchParams
    this.searchParams.startDate = startDate;
    this.searchParams.endDate = endDate;

    this.refresherMembers();
    const dateRangePickerEl = document.getElementById("fbDateRangePicker");
    new DateRangePicker(dateRangePickerEl, {
      autohide: true,
      startDate: startDate,
      endDate: endDate,
    });
  },
};
</script>

<style scoped>
.blur-background {
  backdrop-filter: blur(8px);
  -webkit-backdrop-filter: blur(8px);
}
</style>
