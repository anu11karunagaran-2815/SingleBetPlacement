# Software Test Plan

## 1. Match List & Selection

### TC-1-01: Toggle Odds Selection and Replacement
* **Priority:** Critical
* **Risk Rationale:** If clicking a new odds button fails to override the old selection, users could bet on the wrong match outcome, causing major financial discrepancies.
* **Steps:**
  1. Navigate to the app match list.
  2. Click the 1 (Home Win) button for a Match.
  3. Click the X (Draw) button for the same Match.
* **Expected Result:** The 1 button becomes unselected, the X button transitions to the selected state, and the Bet Slip updates instantly to display the Draw outcome.

### TC-1-02: Display Structural Elements of Pre-Match Football Events
* **Priority:** High
* **Risk Rationale:** Missing data (such as teams or kickoff times) prevents users from verifying what they are betting on, leading to poor usability and compliance risks.
* **Steps:**
  1. Load the application main page.
  2. Inspect the populated match list items.
* **Expected Result:** Every entry displays the Home Team name, Away Team name, a clear Kickoff Date/Time label, and three selectable odds buttons labeled exactly 1, X, and 2.

---

## 2. Bet Slip Display & Mechanics 

### TC-2-01: Single Selection Enforcement
* **Priority:** Critical
* **Risk Rationale:** The application is strictly scoped for single bets. If multiple selections accumulate in the slip, it could break bet logic.
* **Steps:**
  1. Click an odds button on Match A to add it to the slip.
  2. Scroll to Match B and click any odds button.
* **Expected Result:** The fixed right-side Bet Slip removes Match A completely and shows only the newly selected outcome for Match B.

### TC-2-02: Inline Removal and Slip Clearing Actions
* **Priority:** High
* **Risk Rationale:** If users cannot clear their slip easily, they are trapped into either placing an unwanted bet or hard-refreshing the application.
* **Steps:**
  1. Select an outcome to populate the right-side fixed bet slip.
  2. Click the per-selection remove (x) button.
  3. Add another selection, enter a stake, and click the "Remove All" button.
* **Expected Result:** Both actions instantly clear the selection, reset the stake field, and return the Bet Slip to an empty state.

---

## 3. Stake & Balance Validation 

### TC-3-01: Enforce Absolute Minimum and Maximum Stake Limits
* **Priority:** Critical
* **Risk Rationale:** Placing bets below €1.01 or above €100.00 directly violates corporate risk management thresholds and regulatory business logic.
* **Steps:**
  1. Add a valid selection to the Bet Slip.
  2. Enter a stake of 1.00, then click "Place Bet".
  3. Enter a stake of 100.01, then click "Place Bet".
* **Expected Result:** In both instances, UI validation catches the input, blocks API transmission, and displays the respective minimum/maximum error messaging.

### TC-3-02: Prevent Decimal Precision Violations
* **Priority:** High
* **Risk Rationale:** Submitting fractional cents (e.g., three decimal places) creates rounding fragmentation across accounting ledgers.
* **Steps:**
  1. Add a valid selection to the Bet Slip.
  2. Input a stake value of 10.555 and click "Place Bet".
* **Expected Result:** The UI rejects the entry, blocks placement, and warns the user that the stake format is limited to a maximum of 2 decimal places.

### TC-3-03: Evaluate Insufficient Wallet Balance Constraints
* **Priority:** Critical
* **Risk Rationale:** Allowing a user to place a bet that exceeds their available balance leads to an illegal negative wallet balance.
* **Steps:**
  1. Note the current user profile balance (e.g., €50.00).
  2. Enter a stake exactly higher than the balance (e.g., €50.01).
  3. Click "Place Bet".
* **Expected Result:** The system blocks the submission and displays an explicit "Insufficient Balance" warning message.

---

## 4. Bet Placement Interactions 

### TC-4-01: Processing Asynchronous Lifecycle Validation (Success Flow)
* **Priority:** Critical
* **Risk Rationale:** If the UI doesn't visually lock into an in-progress state, users will double-click the placement button, initiating duplicate API requests and multiple charges.
* **Steps:**
  1. Enter a valid stake (€10.00) on an active selection.
  2. Click "Place Bet" and closely observe the initial state changes.
  3. Allow the asynchronous server response to return a success code.
* **Expected Result:** The button changes to a disabled loading state reading "Placing...". Upon server success, the €10.00 stake is instantly deducted from the user's available balance, and the Success Receipt modal appears.

---

## 5. Receipts & Error Handling 

### TC-5-01: Success Receipt Data Integrity & Dismissal
* **Priority:** High
* **Risk Rationale:** Missing legal identifiers (like Bet ID or exact placement odds) violate auditing compliance and leave the user without proof of their wager.
* **Steps:**
  1. Complete a successful bet placement sequence.
  2. Audit all fields rendered inside the emerging Success Receipt modal.
  3. Click the close button on the receipt modal.
* **Expected Result:** The receipt accurately lists the Bet ID, Match Details, Selected Outcome, Stake, Odds at placement, Calculated Potential Payout, and Placement Timestamp. Closing it removes the active selection from the slip and smoothly returns the user to the main browsing view.

### TC-5-02: Error Modal Resilience and Re bet Recovery Flow
* **Priority:** Critical
* **Risk Rationale:** A broken retry system during a network hiccup frustrates users and lowers conversion rates, while a broken exit route leaves the UI locked.
* **Steps:**
  1. Trigger a bet placement that encounters a simulated server drop.
  2. Verify the modal title reads exactly "Something went wrong".
  3. Click the primary action "Rebet" button.
  4. Induce another error, then click the secondary "Close" or top-right X button.
* **Expected Result:** Clicking "Rebet" closes the error modal and instantly retries the API placement request. Clicking "Close" or the X button shuts down the modal window and completely flushes out the current selection and stake parameters from the active workspace.

---

## 6. Filter Configurations 

### TC-6-01: Boundaries for Range-Based Filters
* **Priority:** Medium
* **Risk Rationale:** Flawed range calculations can cause system timeouts or load massive amounts of past or out-of-scope sports data, crashing the client view.
* **Steps:**
  1. Open the Date Filter and choose an inclusive 3-day window of upcoming matches.
  2. Open the Odds Filter and set a valid range (e.g., Min: 1.50, Max: 5.00).
  3. Modify the Odds Filter to an invalid range (Min: 5.00, Max: 1.50).
* **Expected Result:** Valid filters update the match list display instantly. The invalid odds range is rejected with clear error feedback, preserving the previous data state.