DESCRIPTION = "GNOME settings daemon"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=59530bdf33659b29e73d4adb9f9f6552"

PR = "r7"

DEPENDS = "libxxf86misc libsndfile1 libxtst glib-2.0 polkit gtk+ gconf dbus-glib libnotify libgnomekbd libxklavier gnome-doc-utils gnome-desktop"

inherit gnome

SRC_URI = "git://git.gnome.org/gnome-settings-daemon;protocol=git;branch=gnome-2-32"
SRCREV = "0160f6725cfb872e017f3958f108792c3b882872"

S = "${WORKDIR}/git"

EXTRA_OECONF = "--disable-esd \
                --x-includes=${STAGING_INCDIR} \
                --x-libraries=${STAGING_LIBDIR} \
                --enable-polkit \
               "

do_configure_prepend() {
	sed -i -e 's:-L$libdir::g' -e 's:-I$includedir::g' configure.ac
}

FILES_${PN} += "${libdir}/gnome-settings-daemon-2.0/*.so ${libdir}/gnome-settings-daemon-2.0/*plugin \
                ${datadir}/dbus-1/ \
                ${datadir}/icon* \
                ${datadir}/gnome-control-center \
                ${datadir}/xsession*"

FILES_${PN}-dbg += "${libdir}/gnome-settings-daemon-2.0/.debug"
FILES_${PN}-dev += "${libdir}/gnome-settings-daemon-2.0/*.la"
FILES_${PN}-staticdev += "${libdir}/gnome-settings-daemon-2.0/*.a"

