// JaCaMo house building example formulated in Moise DSL
// Last line of this file has to be the "OS" construction

// SS
def house_owner = Role named "house_owner"
def building_company = Role named "building_company"
def site_prep_contractor = Role named "site_prep_contractor" complements building_company
def bricklayer = Role named "bricklayer" complements building_company
def roofer = Role named "roofer" complements building_company
def window_fitter = Role named "window_fitter" complements building_company
def door_fitter = Role named "door_fitter" complements building_company
def plumber = Role named "plumber" complements building_company
def electrician = Role named "electrician" complements building_company
def painter = Role named "painter" complements building_company

def auth_link = Link from house_owner to building_company is_not_valid_for subgroups in one.direction expresses authority inside group
def com_link = Link from building_company to house_owner is_not_valid_for subgroups in one.direction expresses communication inside group
def company_compa = Compatibility from building_company to building_company in one.direction inside group
def house_group = Group named "house_group" with_links (auth_link, com_link) with_compatibilities (company_compa) consists_of (house_owner exactly 1.times) and (site_prep_contractor exactly 1.times) and (bricklayer exactly 1.times) and (roofer exactly 1.times) and (window_fitter exactly 1.times) and (door_fitter exactly 1.times) and (plumber exactly 1.times) and (electrician exactly 1.times) and (painter exactly 1.times)

// FS
def site_prepared = Goal named "site_prepared" described_as "prepare the site for the next tasks" to reach in 20.minutes
def floors_laid = Goal named "floors_laid" to reach in 25.minutes
def walls_built = Goal named "walls_built" to reach in 40.minutes
def roof_built = Goal named "roof_built" to reach in 30.minutes
def windows_fitted = Goal named "windows_fitted" to reach in 10.minutes
def doors_fitted = Goal named "doors_fitted" to reach in 10.minutes
def plumbing_installed = Goal named "plumbing_installed" to reach in 20.minutes
def electrical_system_installed = Goal named "electrical_system_installed" to reach in 20.minutes
def exterior_painted = Goal named "exterior_painted" to reach in 20.minutes
def interior_painted = Goal named "interior_painted" to reach in 30.minutes

def house_built = site_prepared then floors_laid then walls_built then (windows_fitted parallel_with roof_built parallel_with doors_fitted) then (plumbing_installed parallel_with electrical_system_installed parallel_with exterior_painted) then interior_painted named "house_built"

def management_of_house_building = Mission named "management_of_house_building" to_reach house_built by_exactly 1.agent
def prepare_site = Mission named "prepare_site" to_reach site_prepared by_exactly 1.agent
def lay_floors = Mission named "lay_floors" to_reach floors_laid by_exactly 1.agent
def build_walls = Mission named "build_walls" to_reach walls_built by_exactly 1.agent
def build_roof = Mission named "build_roof" to_reach roof_built by_exactly 1.agent
def fit_windows = Mission named "fit_windows" to_reach windows_fitted by_exactly 1.agent
def fit_doors = Mission named "fit_doors" to_reach doors_fitted by_exactly 1.agent
def install_plumbing = Mission named "install_plumbing" to_reach plumbing_installed by_exactly 1.agent
def install_electrical_system = Mission named "install_electrical_system" to_reach electrical_system_installed by_exactly 1.agent
def paint_house = Mission named "paint_house" to_reach (exterior_painted, interior_painted) by_exactly 1.agent

def build_house_sch = Scheme with_goal house_built named "build_house_sch" with_missions (management_of_house_building, prepare_site, lay_floors, build_walls, build_roof, fit_windows, fit_doors, install_plumbing, install_electrical_system, paint_house)

// NS
def n1 = Norm named "n1" obligates house_owner participation_in management_of_house_building valid_for 2.minutes
def n2 = Norm named "n2" obligates site_prep_contractor participation_in prepare_site
def n3 = Norm named "n3" obligates bricklayer participation_in lay_floors
def n4 = Norm named "n4" obligates bricklayer participation_in build_walls
def n5 = Norm named "n5" obligates roofer participation_in build_roof
def n6 = Norm named "n6" obligates window_fitter participation_in fit_windows
def n7 = Norm named "n7" obligates door_fitter participation_in fit_doors
def n8 = Norm named "n8" obligates plumber participation_in install_plumbing
def n9 = Norm named "n9" obligates electrician participation_in install_electrical_system
def n10 = Norm named "n10" obligates painter participation_in paint_house

// OS
OS with_ss { SS with_group (house_group) with_roles (house_owner, building_company, site_prep_contractor, bricklayer, roofer, window_fitter, door_fitter, plumber, electrician, painter) } with_ns { NS with_norms (n1, n2, n3, n4, n5, n6, n7, n8, n9, n10) } with_fs { FS with_schemes build_house_sch } named "house_construction"
